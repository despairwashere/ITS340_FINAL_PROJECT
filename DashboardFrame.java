import javax.swing.*;

public class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton patientBtn = new JButton("Manage Patients");
        patientBtn.addActionListener(e -> new PatientForm().setVisible(true));

        JPanel panel = new JPanel();
        panel.add(patientBtn);
        add(panel);
    }
}
