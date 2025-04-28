public class QuestionFactory {
    public static Question createQuestion(String type, String text) {
        if (type.equalsIgnoreCase("yesno")) {
            return new YesNoQuestion(text);
        }
        return null;
    }
}