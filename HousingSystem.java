
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class HousingSystem {

    private Connection conn = null;

    HousingSystem() throws SQLException {
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

    public void createResident(String ID,

    public void bookHousing(ArrayList<HousingUnit> preferences) throws SQLException {

    }


}

