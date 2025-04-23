import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/patientdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Webster0327!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
