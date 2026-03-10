package Resources;

import java.util.Scanner;

public class DangerAlertSystem {

    private boolean alertActive = false;

    public void activateAlert(EmergencyManager manager) {

        alertActive = true;

        System.out.println("=== ALERTA DE PELIGRO INMINENTE ===");

        // 1 Obtener ubicación (simulada)
        String location = getLocation();

        System.out.println("Ubicación detectada: " + location);

        // 2 Aviso inicial
        System.out.println("Avisando a contactos de posible peligro...");

        System.out.println("Pulsa 'ok' para confirmar que estás bien.");

        waitForUserResponse(manager, location);
    }

    private String getLocation() {

        // simulación de GPS
        return "Ubicación simulada del usuario";
    }

    private void waitForUserResponse(EmergencyManager manager, String location) {

        Scanner sc = new Scanner(System.in);

        int attempts = 3;

        while (attempts > 0 && alertActive) {

            System.out.print("Respuesta del usuario: ");

            String response = sc.nextLine();

            if (response.equalsIgnoreCase("ok")) {

                System.out.println("Usuario confirmado. Cancelando alerta.");
                alertActive = false;
                return;
            }

            attempts--;

            System.out.println("Sin confirmación válida. Intentos restantes: " + attempts);
        }

        if (alertActive) {

            System.out.println("Sin respuesta del usuario. Enviando alerta de emergencia.");

            sendEmergencyAlert(manager, location);
        }
    }

    private void sendEmergencyAlert(EmergencyManager manager, String location) {

        UserData user = new UserData(
                "Usuario",
                "000000000",
                "propietario"
        );

        EmergencyEvent event = new EmergencyEvent(
                "Peligro inminente",
                location,
                user,
                3
        );

        AlertSender sender = new AlertSender();

        sender.sendAlert(event);
    }
}