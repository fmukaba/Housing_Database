
import java.sql.*;
import java.lang.*;
import java.util.*;

public class HousingSystem {

    private static Connection conn = null;

    HousingSystem() throws SQLException, ClassNotFoundException {
        // sets up connection with mySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/HOUSING?serverTimezone=UTC&useSSL=TRUE";

        // LET THEM LOG IN MANUALLY??
        this.conn = DriverManager.getConnection(url, "student", "password");
    }

    // Runs the queries based on the input
    public ArrayList<HousingUnit> checkAvailability() throws SQLException {
        ArrayList<HousingUnit> units = new ArrayList<HousingUnit>();
        String query = "SELECT DISTINCT Building_number, Bedroom_number, Housing_type, Married_couples_allowed," +
                "Price_quarter, count(*) from HOUSING_UNIT where Occupation_status = 0 group by Building_number, " +
                "Bedroom_number, Housing_type, Married_couples_allowed, Price_quarter";
        PreparedStatement p = conn.prepareStatement(query);

        ResultSet r = p.executeQuery();

        while (r.next()) {
            int building = r.getInt(1);

            int bedrooms = r.getInt(2);

            String type = r.getString(3);

            boolean married = r.getBoolean(4);

            int price = r.getInt(5);
            HousingUnit hu = new HousingUnit(building, bedrooms, type, married, price);
            units.add(hu);
        }

        return units;
    }

    public void createUser(String ID, String username, String password, String name, String gender,
                                boolean studentStatus, boolean maritalStatus, String address, String phoneNumber,
                                String college, String department, String major, String familyHeadID)
            throws SQLException {
        String query = "INSERT INTO USER(ID_number, Username, Password, Name, Gender," +
                "                             Student_status, Marital_Status,Address, Phone_number," +
                "                             College, Department, Major, Family_head_ID)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement p = conn.prepareStatement(query);
        p.setString(1, ID);
        p.setString(2, username);
        p.setString(3, password);
        p.setString(4, name);
        p.setString(5, gender);
        p.setBoolean(6, studentStatus);
        p.setBoolean(7, maritalStatus);
        p.setString(8, address);
        p.setString(9, phoneNumber);
        p.setString(10, college);
        p.setString(11, department);
        p.setString(12, major);
        p.setString(13, familyHeadID);
        p.execute();
    }

    // Book applicant depending on availability of preferences
    public boolean bookHousing(String SID, ArrayList<HousingUnit> preferences, String roommate) throws SQLException {
        int bNo = 0;
        int aptNo = 0;
        String query = "SELECT H.Occupation_status, H.Apt_Number, H.Building_number from HOUSING_UNIT H where " +
                "H.Apt_number =(SELECT R.Apt_number from RESIDENT R where R.ID_number =" +
                "(SELECT U.ID_number from USER U where Name = ?))";
        PreparedStatement p = conn.prepareStatement(query);
        p.setString(1, roommate);
        ResultSet r = p.executeQuery();
        if (r.next()) {                             // roommate was found in the system
            int occupied = r.getInt(1);
            if (occupied == 0) {
                aptNo = r.getInt(2);
                bNo = r.getInt(3);
                createResident(SID, aptNo, bNo);
                return true;
            }
        }
        ArrayList<HousingUnit> availableUnits = checkAvailability();
        for (HousingUnit pref : preferences) {
            for (HousingUnit avail : availableUnits) {
                if (pref.equals(avail)) {
                    String query3 = "SELECT Apt_number, Building_number from HOUSING_UNIT WHERE Building_number = ? and " +
                            "Bedroom_number = ? and Housing_type = ? and Occupation_status = 0 Limit 1";
                    PreparedStatement p2 = conn.prepareStatement(query3);
                    p2.setInt(1, avail.getBuilding());
                    p2.setInt(2, avail.getBedrooms());
                    p2.setString(3, avail.getType());
                    ResultSet r2 = p2.executeQuery();
                    aptNo = r2.getInt(1);
                    bNo = r2.getInt(2);
                    createResident(SID, aptNo, bNo);
                    return true;
                }
            }
        }

        //Booking did not go through
        createApplicant(SID, preferences, roommate);
        return false;
    }

    // If Booking goes through, add Resident
    public void createResident(String ID, int aptNo, int bNo) throws SQLException {
        String query1 = "SELECT Staff_ID FROM ADMINISTRATOR WHERE Dept_name = \"Residency\" ORDER BY RAND() Limit 1";
        PreparedStatement p1 = conn.prepareStatement(query1);
        ResultSet r = p1.executeQuery();
        String adminStaffID = r.getString(1);

        String query2 = "INSERT INTO RESIDENT(ID_number, Admin_staff_ID, Move_in_date, Check_out_date, " +
                "Building_number, Apartment_number, Rent_till_date)" +
                " VALUES (?, ?, NULL, NULL, ?, ?, 0)";
        PreparedStatement p2 = conn.prepareStatement(query2);
        p2.setString(1, ID);
        p2.setString(2, adminStaffID);
        p2.setInt(3, bNo);
        p2.setInt(4, aptNo);
        p2.execute();

        // Change occupation status if unit gets filled
        String query3 = "SELECT Count(*) from RESIDENT where Apt_number = ?"; // how many current residents in unit
        PreparedStatement p3 = conn.prepareStatement(query3);
        p2.setInt(1, aptNo);
        ResultSet r3 = p3.executeQuery();
        int current = r3.getInt(1);

        String query4 = "SELECT Max_people from HOUSING_UNIT where Apt_number = ?"; // max number of residents in unit
        PreparedStatement p4 = conn.prepareStatement(query4);
        p4.setInt(1, aptNo);
        ResultSet r4 = p4.executeQuery();
        int max = r4.getInt(1);

        // check if unit is filled to capacity
        if (current == max) {
            String query5 = "UPDATE HOUSING_UNIT SET Occupation_status = 1 where Apt_number = ?";
            PreparedStatement p5 = conn.prepareStatement(query5);
            p5.setInt(1, aptNo);
            p5.execute();
        }

    }

    // Add applicant who did not get into the system (wait list)
    public static void createApplicant(String ID, ArrayList<HousingUnit> preferences, String roommate) throws SQLException {

        String query = "INSERT INTO APPLICANT(ID_number, Acceptance_status) VALUES (?, 0)";
        PreparedStatement p = conn.prepareStatement(query);
        p.setString(1, ID);
        p.execute();

        int index = 1;
        for (HousingUnit h : preferences) {
            String query2 = "INSERT INTO HOUSING_PREFERENCES(ID_number, Building_preference, Housing_type_preference, " +
                    "Bedroom_preference, Order_of_preference, Roommate_preference) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement p2 = conn.prepareStatement(query2);
            p2.setString(1, ID);
            p2.setInt(2, h.getBuilding());
            p2.setString(3, h.getType());
            p2.setInt(4, h.getBedrooms());
            p2.setInt(5, index);
            p2.setString(6, roommate);
            p2.execute();

            index++;
        }
    }

    public static ArrayList<MaintenanceRequestDue> runReports() {
        ArrayList<MaintenanceRequestDue> list = new ArrayList<MaintenanceRequestDue>();
        String CurrentDate = "";
        System.out.println("Active maintenance requests: ");

        try {
            String query = "SELECT USER.Name, Building_number, Apt_number, Submission_date, Date_Completed, Comments\r\n"
                    + "FROM RESIDENT, MAINTENANCE_REQUEST, USER\r\n"
                    + "WHERE RESIDENT.ID_NUMBER = USER.ID_NUMBER and Submission_date <= ? "; // currentDate
            PreparedStatement p = conn.prepareStatement(query);
            p.setString(1, CurrentDate);
            p.clearParameters();
            ResultSet r = p.executeQuery();
            System.out.println("\nRESULTS: ");
            while (r.next()) {
                MaintenanceRequestDue row = new MaintenanceRequestDue(r.getString(1), r.getString(2), r.getString(3),
                        r.getString(4), r.getString(5), r.getString(6));
                list.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

}

