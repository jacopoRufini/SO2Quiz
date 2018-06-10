import java.io.*;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    Reader reader = new Reader("dependencies/questions.txt");
    List<Question> questions = reader.readFile();
    Quiz quiz = new Quiz(questions);
    quiz.run();
  }
}
