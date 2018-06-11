import java.util.Arrays;
import java.util.Scanner;

public class InputThread extends Thread {

    private char input;
    public boolean finished;
    public static Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        String input = sc.next();
        char c = input.charAt(0);

        while (!isInterrupted() && (input.length() != 1 || !Arrays.asList('a', 'b', 'c', 'd', 's').contains(c))) {
            System.out.println("Risposta non valida! Seleziona una risposta tra 'a - d' o 's' per skippare");
            input = sc.next();
            c = input.charAt(0);
        }

        this.input = c;
        this.finished = true;
        if (!this.isInterrupted())
            this.interrupt();
    }

    public char getInput() {
        return this.input;
    }

    public boolean hasFinished(){
        return this.finished;
    }

}
