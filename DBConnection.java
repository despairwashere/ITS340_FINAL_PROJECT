import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for obtaining database connections.
 * 
 * Configuration is read from environment variables:
 * - DB_URL (default: jdbc:mysql://localhost:3307/patientdb)
 * - DB_USER (default: root)
 * - DB_PASSWORD (default: empty)
 */
public class DBConnection {
    private static final String URL = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3307/patientdb");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "Webster0327!");

    /**
     * Returns a new database connection.
     * 
     * @return a valid SQL Connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            throw e;
        }
    }
}
