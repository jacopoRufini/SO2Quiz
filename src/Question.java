public class Question {
    private String question;
    private String[] answers;
    private char rightAnswer;

    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
        this.rightAnswer = ' ';
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public char getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(char rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

}
