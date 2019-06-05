import java.util.Timer;
import java.util.TimerTask;

public class TimerThread extends Thread {

    private int secondsPassed = 0;
    private int minutesPassed = 0;
    private int timeoutSeconds;
    private int timeoutMinutes;
    private boolean timedOut = false;

    public TimerThread(int timeoutMinutes, int timeoutSeconds) {
        super();
        this.timeoutMinutes = timeoutMinutes;
        this.timeoutSeconds = timeoutSeconds;
    }

    private Timer myTimer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            System.out.print("\r");
            secondsPassed++;
            if (secondsPassed == 60) {
                minutesPassed++;
                secondsPassed = 0;
            }
            String out = "";
            if (minutesPassed < 10)
                out += "0";
            out += minutesPassed;
            out += ":";
            if (secondsPassed < 10)
                out += "0";
            out += secondsPassed;
            System.out.print(out + "\t");
        }

    };

    public boolean timedOut() {
        return this.timedOut;
    }

    @Override
    public void run() {
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
        try {
            Thread.currentThread().sleep((timeoutMinutes * 60 + timeoutSeconds) * 1000 + 100);
        } catch (InterruptedException e) {this.timedOut = true;}

        myTimer.cancel();
        this.timedOut = !this.timedOut;
        if (this.timedOut) {
            System.out.println("\n\nTempo scaduto!");
            System.out.println("Premi qualsiasi tasto e fai ENTER per vedere i risultati");
        }
        if (!this.isInterrupted())
            this.interrupt();
    }
}
