public class YesNoQuestion implements Question {
    private String question;
    private String answer;

    public YesNoQuestion(String question) {
        this.question = question;
    }

    public void display() {
        System.out.println("(Yes/No) " + question);
    }

    public String getText() { return question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}