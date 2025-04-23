import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });

        try (Connection conn = DBConnection.getConnection()) {
            if (conn.isValid(2)) {
                System.out.println("Connection is valid and successful!");
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
}