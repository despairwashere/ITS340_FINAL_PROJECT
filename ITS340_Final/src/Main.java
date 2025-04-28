public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            InterviewView view = new InterviewView();
            new InterviewController(view);
        });
    }
}