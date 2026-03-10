package Resources.Notification;
import java.time.LocalDateTime;

public class Notification {
    private String message;
    private LocalDateTime triggerTime;

    public Notification(String message, LocalDateTime triggerTime) {
        this.message = message;
        this.triggerTime = triggerTime;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    @Override
    public String toString() {
        return "NOTIFICACIÓN: " + message + " | Hora: " + triggerTime;
    }
}