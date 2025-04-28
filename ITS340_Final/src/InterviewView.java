import javax.swing.*;
import java.awt.*;

public class InterviewView extends JFrame {
    public JPanel panel; // Made panel public
    public JTextField nameField = new JTextField(20);
    public JTextField ageField = new JTextField(3);
    public JTextArea questionArea = new JTextArea(3, 30);
    public JTextField answerField = new JTextField(30);
    public JButton submitButton = new JButton("Submit");

    public InterviewView() {
        setTitle("Patient Interview");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(); // Initialized panel
        panel.setLayout(new GridLayout(7, 1)); // Adjusted layout to accommodate all components
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(questionArea);
        panel.add(answerField);
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }
}
