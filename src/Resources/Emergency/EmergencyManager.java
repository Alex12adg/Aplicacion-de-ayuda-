package Resources.Emergency;
import Resources.Data.JsonDataLoader;
import Resources.Session.UserSession;
import Resources.User.UserData;

import java.util.List;

public class EmergencyManager {

    public void startSystemInteractive() {

        //Cargar centros de emergencia desde JSON
        List<EmergencyCenter> centers = JsonDataLoader.loadCenters("SRC/Resources/Location/centers.json");

        System.out.println("======================================");
        System.out.println("   CENTROS DE EMERGENCIA DISPONIBLES");
        System.out.println("======================================");

        if (centers.isEmpty()) {
            System.out.println("No se han podido cargar centros desde el JSON.");
        } else {
            for (EmergencyCenter c : centers) {
                System.out.println(" - " + c);
            }
        }

        System.out.println("\n======================================");
        System.out.println("   SISTEMA DE DETECCIÓN DE EMERGENCIAS");
        System.out.println("======================================");

        //Sistema actual
        EmergencyDetector detector = new EmergencyDetector(5);
        EmergencyEvent event = detector.detectEventInteractive();

        if (event != null) {
            AlertSender sender = new AlertSender();
            sender.sendAlert(event);

            //Mostrar servicios útiles tras la emergencia
            if (!centers.isEmpty()) {
                System.out.println("\n=== SERVICIOS DE EMERGENCIA EN LA ZONA ===");
                for (EmergencyCenter c : centers) {
                    System.out.println(c.getType() + " -> " + c.getName());
                }
            }

        } else {
            System.out.println("No se activó ninguna emergencia.");
        }
    }

    public void triggerVoiceEmergency() {

        System.out.println("Activación de emergencia mediante voz.");

        try {
            UserData user = UserSession.getUser();

            if (user == null) {
                throw new Exception("No hay usuario en sesión");
            }

            EmergencyEvent event = new EmergencyEvent(
                    "Activación por palabra clave",
                    "Ubicación pendiente",
                    user,
                    3
            );

            AlertSender sender = new AlertSender();
            sender.sendAlert(event);

        } catch (Exception e) {
            System.out.println("Error al activar emergencia: " + e.getMessage());
        }
    }
}