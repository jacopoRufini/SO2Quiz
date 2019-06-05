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

    public List<Question> readFile() throws IOException {
        try {
            Question question;
            String line;
            String next;
            String[] answers;
			int[] orderAnswers;
			int rand;
            char rightAnswer;
            while ((line = br.readLine()) != null) {
				List<Integer> options = new ArrayList<>(List.of(0,1,2,3));
				orderAnswers = new int[4]; 
                answers = new String[4];
                for (int i = 0; i < 4; i++) {
                    next = br.readLine();
					rand = randomFromList(options); 
					options.remove(Integer.valueOf(rand)); 
                    answers[rand] = next;
					orderAnswers[i] = rand;
                }
                rightAnswer = br.readLine().charAt(0);
                question = new Question(line, answers);
				switch(rightAnswer)
				{
					case 'a':
						rightAnswer = orderAnswerToChar(orderAnswers[0]);
						break;
					case 'b':
						rightAnswer = orderAnswerToChar(orderAnswers[1]);
						break;
					case 'c':
						rightAnswer = orderAnswerToChar(orderAnswers[2]);
						break;
					case 'd':
						rightAnswer = orderAnswerToChar(orderAnswers[3]);
						break;
				}
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
	
	private int randomFromList(List<Integer> list) {
	    int index = (int) (Math.random() * list.size());
	    return list.get(index);   
	}
	
	private char orderAnswerToChar(int orderAnswer) { 
		if (orderAnswer == 0)
			return 'a';
		else if (orderAnswer == 1)
			return 'b';
		else if (orderAnswer == 2)
			return 'c';
		else
			return 'd';
	}

}
