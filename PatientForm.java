import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class PatientForm extends JFrame {
    private JTextField idField, nameField;

    public PatientForm() {
        setTitle("Patient Management");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Patient ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");

        addBtn.addActionListener(e -> addPatient());
        editBtn.addActionListener(e -> editPatient());
        delBtn.addActionListener(e -> deletePatient());

        add(addBtn);
        add(editBtn);
        add(delBtn);
    }

    private void addPatient() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO patientdemographics (id, name) VALUES (?, ?)")) {
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, nameField.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Patient added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editPatient() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE patientdemographics SET name=? WHERE id=?")) {
            stmt.setString(1, nameField.getText());
            stmt.setInt(2, Integer.parseInt(idField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Patient updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletePatient() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM patientdemographics WHERE id=?")) {
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Patient deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
