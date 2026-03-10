package Resources.Emergency;

import java.io.FileWriter;
import java.io.IOException;

public class AlertSender {

    public void sendAlert(EmergencyEvent event) {
        System.out.println("\nEnviando alerta al 112...");
        System.out.println(event.toString());

        try (FileWriter writer = new FileWriter("SRC/Resources/log.txt", true)) {
            writer.write(event.toString() + "\n");
            System.out.println("Alerta registrada en log.txt");
        } catch (IOException e) {
            System.out.println("Error escribiendo en log.txt: " + e.getMessage());
        }

        notifyContacts(event);
    }

    private void notifyContacts(EmergencyEvent event) {
        System.out.println("Notificando a contactos del usuario " + event.getUsuario().getNombre());
    }
}
