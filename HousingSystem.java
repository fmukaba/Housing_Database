
import java.sql.*;
import java.lang.*;
import java.util.*;

public class HousingSystem {

    private Connection conn = null;

    HousingSystem() throws SQLException, ClassNotFoundException {
        // sets up connection with mySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/HOUSING?serverTimezone=UTC&useSSL=TRUE";
        this.conn = DriverManager.getConnection(url, "student", "password");
    }

    // Runs the queries based on the input
    public ArrayList<HousingUnit> checkAvailability() throws SQLException {
        ArrayList<HousingUnit> units = new ArrayList<HousingUnit>();
        String query = "SELECT DISTINCT Building_number, Bedroom_number, Housing_type, count(*) from HOUSING_UNIT" +
                "where Occupation_status = 0 group by Building_number, Bedroom_number, Housing_type";
        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int building = r.getInt(1);
            int bedrooms = r.getInt(2);
            String type = r.getString(3);
            HousingUnit hu = new HousingUnit(building, bedrooms, type);
            units.add(hu);
        }
        return units;
    }

    public void createApplicant(String ID, String username, String password, String name, String gender,
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
    public boolean bookHousing(String SID, ArrayList<HousingUnit> preferences) throws SQLException {
        ArrayList<HousingUnit> availableUnits = checkAvailability();
        for (HousingUnit pref : preferences) {
            for (HousingUnit avail : availableUnits) {
                if (pref.equals(avail)) {
                    createResident(SID, pref);
                    return true;
                }
            }
        }
        return false;
    }

    // If Booking goes through, add Resident
    public void createResident(String ID, HousingUnit hu) throws SQLException {
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
        p2.setInt(3, hu.getBuilding());
        p2.setInt(4, hu.getBedrooms());
    }

}

