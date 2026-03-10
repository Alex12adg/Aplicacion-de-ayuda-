package Resources.Notification;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationScheduler {

    private Timer timer = new Timer();

    public void schedule(Notification notification) {
        long delay = Duration.between(LocalDateTime.now(),
                notification.getTriggerTime()).toMillis();

        if (delay < 0) {
            System.out.println("⚠️ La hora ya pasó. No se puede programar.");
            return;
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                NotificationService.send(notification);
            }
        }, delay);
    }
}
