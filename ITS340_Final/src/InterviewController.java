import java.awt.event.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;

public class InterviewController {
    private InterviewView view;
    private Queue<Question> questions = new LinkedList<>();
    private Question currentQuestion;

    public InterviewController(InterviewView view) {
        this.view = view;

        questions.add(QuestionFactory.createQuestion("yesno", "Do you smoke?"));
        questions.add(QuestionFactory.createQuestion("yesno", "Do you have allergies?"));

        nextQuestion();

        view.submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentQuestion.setAnswer(view.answerField.getText());
                saveToDatabase(view.nameField.getText(), Integer.parseInt(view.ageField.getText()), currentQuestion);
                nextQuestion();
            }
        });
    }

    private void nextQuestion() {
        if (!questions.isEmpty()) {
            currentQuestion = questions.poll();
            view.questionArea.setText(currentQuestion.getText());
        } else {
            view.questionArea.setText("Interview Complete");
            view.panel.remove(view.submitButton);

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> System.exit(0));
            view.panel.add(exitButton);
            view.panel.revalidate();
            view.panel.repaint();
        }
    }

    private void saveToDatabase(String name, int age, Question question) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
    
            // Check if patient already exists
            String checkSql = "SELECT id FROM patients WHERE name = ? AND age = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, name);
            checkStmt.setInt(2, age);
            ResultSet rs = checkStmt.executeQuery();
    
            int patientId;
            if (rs.next()) {
                // Patient exists, retrieve ID
                patientId = rs.getInt("id");
            } else {
                // Patient doesn't exist, insert new record
                String insertSql = "INSERT INTO patients (name, age) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, name);
                insertStmt.setInt(2, age);
                insertStmt.executeUpdate();
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    patientId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve patient ID.");
                }
                insertStmt.close();
            }
            checkStmt.close();
    
            // Insert answer
            String answerSql = "INSERT INTO answers (patient_id, question, answer) VALUES (?, ?, ?)";
            PreparedStatement answerStmt = conn.prepareStatement(answerSql);
            answerStmt.setInt(1, patientId);
            answerStmt.setString(2, question.getText());
            answerStmt.setString(3, question.getAnswer());
            answerStmt.executeUpdate();
            answerStmt.close();
    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}