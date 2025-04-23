import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Check database connection before launching GUI
        if (!isDatabaseConnectionValid()) {
            JOptionPane.showMessageDialog(
                null,
                "Failed to connect to the database. Please check your configuration.",
                "Database Connection Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Launch the login GUI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    /**
     * Checks if the database connection is valid.
     * @return true if connection is valid, false otherwise
     */
    private static boolean isDatabaseConnectionValid() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && conn.isValid(2)) {
                LOGGER.info("Connection is valid and successful!");
                return true;
            } else {
                LOGGER.warning("Connection is not valid.");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database connection failed", e);
            return false;
        }
    }
}
