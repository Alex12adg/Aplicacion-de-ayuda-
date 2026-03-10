package Resources.Emergency;
import Resources.Data.JsonDataLoader;
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

        UserData user = new UserData(
                "Usuario",
                "000000000",
                "propietario"
        );

        EmergencyEvent event = new EmergencyEvent(
                "Activación por palabra clave",
                "Ubicación desconocida",
                user,
                3
        );

        AlertSender sender = new AlertSender();

        sender.sendAlert(event);
    }
}