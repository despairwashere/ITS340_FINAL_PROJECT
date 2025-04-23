import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // UI Components
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        add(loginButton);

        JButton cancelButton = new JButton("Cancel");
        add(cancelButton);

        // Button Actions
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Placeholder login logic
            if (!username.isEmpty() && !password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                // You can redirect to another frame here
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both fields.");
            }
        });

        cancelButton.addActionListener(e -> {
            dispose(); // Close the window
        });
    }
}
