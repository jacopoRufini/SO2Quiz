import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<Question> questions;
    private char[] rightAnswers;
    private char[] givenAnswers;
    private int rightCounter;
    private int blankCounter;
    private int wrongCounter;

    public final static int N_ANSWERS = 40;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        Collections.shuffle(this.questions);
        rightAnswers = new char[N_ANSWERS];
        givenAnswers = new char[N_ANSWERS];
    }


    public void run() {
        System.out.println(
                "Benvenuto nel quiz patent...emh.. di SO2.\n" +
                        "Per rispondere alla domanda è necessario dare in input 'a,b,c,d' oppure 's' per skippare la domanda. \n" +
                        "Per ottenere il risultato è necessario svolgere tutte e 40 le domande prese randomicamente tra le 80 circa disponibili e recenti.\n" +
                        "ATTENZIONE: Le risposte date come 'corrette' non sono state selezionate da me, io ho solo preso un TXT proveniente da uno studente affidabile(spero) con domande e soluzioni.\n" +
                        "Per qualsiasi bug puoi insultarmi qui -----> https://www.facebook.com/jacopo.rufini\n" +
                        "Per ciascuno studente viene calcolato il punteggio come 2E(esatte) − S(sbagliate), quindi il punteggio va da -40 a 80\n" +
                        "Nella directory è presente il TXT con domande,risposte,soluzioni. L'ho reso disponibile in modo che chi vuole può aggiornarlo con il passare degli anni, con sempre più domande, facendo attenzione però a mantenere lo stesso pattern di lettura del file o muore tutto.\n" +
                        "Il jar è originariamente compilato in JAVA 10. Se vivi nel passato e vuoi comunque usare il quiz patente contattami che ti passo le source.\n\n" +
                        "Un grazie particolare a https://www.facebook.com/scacio per il timer e il TXT per risolvere il bug di Windows. \n" +
                        "GOOD LUCK :-)\n\n");

        Question question;
        char letter;

        TimerThread timer = new TimerThread(40, 0);
        timer.start();

        for (int i = 0; i < N_ANSWERS; i++) {
            question = questions.get(i);
            letter = 'a'-1;
            System.out.println(i+1 +".  " + question.getQuestion() + "\n");
            for (String quest: question.getAnswers()) {
                System.out.println("\t" + ++letter + "." + quest);
            }
            System.out.println();

            InputThread threadIn = new InputThread();
            threadIn.start();

            while (!threadIn.isInterrupted() && !timer.isInterrupted()) {
                ;
            }

            if (!timer.isInterrupted()) {
                char input = threadIn.getInput();
                char rightAnswer = question.getRightAnswer();
                if (input == rightAnswer)
                    rightCounter++;
                else if (input == 's')
                    blankCounter++;
                else
                    wrongCounter++;
                rightAnswers[i] = rightAnswer;
                givenAnswers[i] = input;
            } else {
                threadIn.interrupt();
                for (int j = i; j < N_ANSWERS; j++) {
                    blankCounter++;
                    rightAnswers[j] = question.getRightAnswer();
                    givenAnswers[j] = 's';
                }

                System.out.println("\n\nTempo scaduto!");
                System.out.println("Premi qualsiasi tasto e fai ENTER per vedere i risultati");

                while (!threadIn.hasFinished()) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                break;
            }
        }

        if (!timer.isInterrupted())
            timer.interrupt();

        InputThread.sc.close();
        result();
    }

    private void result() {
        System.out.println("\nRisposte date\t|\tRisposte corrette");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < N_ANSWERS; i++) {
            if (givenAnswers[i] != rightAnswers[i])
                System.out.println(i+1 + "\t" + givenAnswers[i] +"\t|\t\t"+ rightAnswers[i]);
            else
                System.out.println(i+1 + "\t" + givenAnswers[i] +"\t|\t\t"+ "✓");
        }
        System.out.println("\n\nRisposte corrette -> " + rightCounter);
        System.out.println("Risposte lasciate -> " + blankCounter);
        System.out.println("Risposte errate -> " + wrongCounter);
        System.out.println("\nPunteggio -> " + (2 * rightCounter - wrongCounter) +"/" + N_ANSWERS);

    }
}
