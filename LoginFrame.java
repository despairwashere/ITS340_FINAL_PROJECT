import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton cancelButton;

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        usernameLabel.setLabelFor(usernameField);

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        passwordLabel.setLabelFor(passwordField);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        // Set loginButton as default
        getRootPane().setDefaultButton(loginButton);

        // Button Actions
        loginButton.addActionListener(e -> handleLogin());
        cancelButton.addActionListener(e -> dispose());

        // Focus on username field
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent e) {
                usernameField.requestFocusInWindow();
            }
        });

        pack();
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();

        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter both fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            passwordField.setText("");
            return;
        }

        // TODO: Replace with real authentication logic
        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            // Redirect to another frame here
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }

        // Zero out password for security
        java.util.Arrays.fill(password, '\0');
    }

    /**
     * Placeholder for authentication logic.
     * Replace with actual authentication against a database or service.
     */
    private boolean authenticate(String username, char[] password) {
        // Example: accept any non-empty credentials
        return !username.isEmpty() && password.length > 0;
    }
}
