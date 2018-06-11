import java.util.Timer;
import java.util.TimerTask;

public class TimerThread extends Thread {

    private int secondsPassed = 0;
    private int minutesPassed = 0;
    private int timeoutSeconds;
    private int timeoutMinutes;

    public TimerThread(int timeoutMinutes, int timeoutSeconds) {
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

    private boolean timedOut() {
        return !(minutesPassed < timeoutMinutes || secondsPassed < timeoutSeconds);
    }

    @Override
    public void run() {
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
        while (!this.isInterrupted() && !timedOut()) {
            ;
        }

        myTimer.cancel();
        if (timedOut()) {
            this.interrupt();
        }
    }
}
