import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quiz {
  private List<Question> questions;
  private char input;
  private char[] rightAnswers;
  private char[] givenAnswers;
  private int rightCounter;
  private int blankCounter;
  private int wrongCounter;

  public final static int N_ANSWERS = 40;

  public Quiz(List<Question> questions) {
    this.questions = questions;
    Collections.shuffle(this.questions);
    rightAnswers = new char[40];
    givenAnswers = new char[40];
  }

  private void giveInput() {
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    char c = input.charAt(0);
    while(input.length() != 1 || !Arrays.asList('a','b','c','d','s').contains(c)) {
      input = sc.next();
      c = input.charAt(0);
      System.out.println("Seleziona una risposta tra 'a - d' o 's' per skippare");
    }
    this.input = c;

  }

  public void run() {
    System.out.println(
    "Benvenuto nel quiz patent...emh.. di SO2.\n" +
    "Per rispondere alla domanda è necessario dare in input 'a,b,c,d' oppure 's' per skippare la domanda. \n" +
    "Per ottenere il risultato è necessario svolgere tutte e 40 le domande prese randomicamente tra le 80 circa disponibili e recenti.\n" +
    "ATTENZIONE: Le risposte date come 'corrette' non sono state selezionate da me, io ho solo preso un TXT proveniente da uno studente affidabile(spero) con domande e soluzioni.\n" +
    "Per qualsiasi bug puoi insultarmi qui -----> https://www.facebook.com/jacopo.rufini\n" +
    "Nella directory è presente il TXT con domande,risposte,soluzioni. L'ho reso disponibile in modo che chi vuole può aggiornarlo con il passare degli anni, con sempre più domande, facendo attenzione però a mantenere lo stesso pattern di lettura del file o muore tutto.\n" +
    "Il jar è originariamente compilato in JAVA 10. Se vivi nel passato e vuoi comunque usare il quiz patente contattami che ti passo le source.\n\n" +
    "GOOD LUCK :-)\n\n");

    Question question;
    char letter;
    for (int i = 0; i < N_ANSWERS; i++) {
      question = questions.get(i);
      letter = 'a'-1;
      System.out.println(i+1 +".  " + question.getQuestion() + "\n");
      for(String q: question.getAnswers()) {
        System.out.println("\t" + ++letter + "." + q);
      }
      giveInput();

      if (this.input == question.getRightAnswer()) rightCounter++;
      else if(this.input == 's') blankCounter++;
      else if(this.input != question.getRightAnswer()) wrongCounter++;
      rightAnswers[i] = question.getRightAnswer();
      givenAnswers[i] = this.input;
    }
    result();
  }

  private void result() {
    System.out.println("Risposte date  |  Risposte corrette");
    for (int i = 0; i < N_ANSWERS; i++) {
      System.out.println(i+1 + "  " + givenAnswers[i] +"\t\t "+ rightAnswers[i]);
    }
    System.out.println("\n\nRisposte corrette -> " + rightCounter);
    System.out.println("Risposte lasciate -> " + blankCounter);
    System.out.println("Risposte errate -> " + wrongCounter);
    System.out.println("\nPunteggio -> " + (rightCounter - wrongCounter) +"/40");

  }
}
