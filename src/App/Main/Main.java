package App.Main;
import Resources.EmergencyManager;
import Resources.Notification;
import Resources.NotificationScheduler;

import java.time.LocalDateTime;
import java.util.Scanner;

/*** Punto de entrada.***/
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE EMERGENCIAS ===");
        EmergencyManager manager = new EmergencyManager();
        manager.startSystemInteractive();
    }
    {
        Scanner sc = new Scanner(System.in);
        NotificationScheduler scheduler = new NotificationScheduler();

        System.out.println("=== SISTEMA DE RECORDATORIOS ===");
        System.out.print("Mensaje del recordatorio: ");
        String message = sc.nextLine();

        System.out.print("¿En cuántos minutos quieres que se avise?: ");
        int minutes = sc.nextInt();

        LocalDateTime triggerTime = LocalDateTime.now().plusMinutes(minutes);

        Notification notification = new Notification(message, triggerTime);
        scheduler.schedule(notification);

        System.out.println("Recordatorio programado correctamente.");
        System.out.println("La aplicación debe permanecer abierta.");
    }
}

