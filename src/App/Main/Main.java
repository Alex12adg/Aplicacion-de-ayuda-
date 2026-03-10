package App.Main;
import Resources.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/*** Punto de entrada.***/
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmergencyManager manager = new EmergencyManager();
        NotificationScheduler scheduler = new NotificationScheduler();

        VoiceConfig voiceConfig = new VoiceConfig();
        VoiceDetector voiceDetector = new VoiceDetector(voiceConfig);

        boolean running = true;

        while (running) {

            System.out.println("\n=== SISTEMA DE EMERGENCIAS ===");
            System.out.println("1. Sistema de emergencias");
            System.out.println("2. Programar recordatorio");
            System.out.println("3. Activar detección de voz (simulada)");
            System.out.println("4. Cambiar palabra clave de voz");
            System.out.println("5. Sistema de peligro inminente");
            System.out.println("6. Salir");

            System.out.print("Selecciona una opción: ");

            int option;

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada no válida.");
                continue;
            }

            switch (option) {

                case 1:

                    manager.startSystemInteractive();

                    break;

                case 2:

                    System.out.println("=== SISTEMA DE RECORDATORIOS ===");

                    System.out.print("Mensaje del recordatorio: ");
                    String message = sc.nextLine();

                    System.out.print("¿En cuántos minutos quieres que se avise?: ");
                    int minutes = Integer.parseInt(sc.nextLine());

                    LocalDateTime triggerTime = LocalDateTime.now().plusMinutes(minutes);

                    Notification notification = new Notification(message, triggerTime);

                    scheduler.schedule(notification);

                    System.out.println("Recordatorio programado correctamente.");

                    break;

                case 3:

                    System.out.println("Activando sistema de detección de voz...");

                    Thread voiceThread = new Thread(() ->
                            voiceDetector.startListening(manager)
                    );

                    voiceThread.start();

                    break;

                case 4:

                    System.out.print("Nueva palabra clave: ");
                    String keyword = sc.nextLine();

                    voiceConfig.setKeyword(keyword);

                    System.out.println("Palabra clave actualizada.");

                    break;

                case 5:

                    DangerAlertSystem dangerAlertSystem = new DangerAlertSystem();
                    System.out.println("Activar alerta de peligro inminente");
                    dangerAlertSystem.activateAlert(manager);
                    break;

                case 6:

                    System.out.println("Cerrando sistema...");
                    running = false;

                    break;
                default:

                    System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }
}
