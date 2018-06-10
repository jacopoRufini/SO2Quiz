import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

  private BufferedReader br;
  private File file;
  private List<Question> questions;

  public Reader(String path) throws FileNotFoundException {
    file = new File(path);
    br = new BufferedReader(new FileReader(file));
    questions = new ArrayList<>();
  }

  public List<Question> readFile() throws IOException{
    try {
      Question question;
      String line;
      String next;
      String[] answers;
      char rightAnswer;
      while ((line = br.readLine()) != null) {
        answers = new String[4];
        for (int i = 0; i < 4; i++) {
          next = br.readLine();
          answers[i] = next;
        }
        rightAnswer = br.readLine().charAt(0);
        question = new Question(line,answers);
        question.setRightAnswer(rightAnswer);
        questions.add(question);
        br.readLine();
      }
      } catch (IOException e) {
        e.printStackTrace();
    }
    br.close();
    return questions;
  }

}
