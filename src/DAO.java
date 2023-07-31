import java.sql.*;
import java.util.List;

public class DAO {
    String url = "jdbc:mysql://localhost:3306/medicy";
    String username = "root";
    String password = "1234";


    private static Connection getConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    public void createTable(){
        Connection conn = null;
        Statement stmt =null;
    try

    {
        conn = getConnection(url, username, password);
        stmt = conn.createStatement();
        String sql = "CREATE TABLE `medicy`.`medicine` ("
                + "`idMedicine` INT NOT NULL AUTO_INCREMENT,"
                + "`name` VARCHAR(200) NOT NULL,"
                + "`form` VARCHAR(100) NOT NULL,"
                + "`marketingStatus` VARCHAR(45) NOT NULL,"
                + "`approvalDate` DATE NOT NULL,"
                + "`price` decimal(10,4) ,"
                + "PRIMARY KEY (`idMedicine`))";

        stmt.executeUpdate(sql);
        System.out.println("Table created successfully!");
    } catch(SQLException e) {
        e.printStackTrace();
    } finally

    {
        // Close resources
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

    public void add(List<Medicine> medicines) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `medicy`.`medicine` (`idMedicine`, `name`, `form`, `marketingStatus`, `approvalDate`, `price`) VALUES (?, ?, ?, ?, ?, ?)");
            for (Medicine medicine : medicines) {
                ps.setString(1, medicine.getId());
                ps.setString(2, medicine.getName());
                ps.setString(3, medicine.getForm());
                ps.setString(4, medicine.getMarketingStatus());
                ps.setDate(5, Date.valueOf(medicine.getApprovalDate()));
                ps.setFloat(6, medicine.getPrice());
                int rows = ps.executeUpdate();
                System.out.println(rows + " row(s) inserted.");
            }
        } finally {
            // close database connection
            if (conn != null) {
                conn.close();
            }
        }
    }
}

