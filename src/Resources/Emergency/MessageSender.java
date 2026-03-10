package Resources.Emergency;

import Resources.User.UserData;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*** Simula envío de mensajes/llamadas/SMS.
 * Persistencia simple en messages_log.txt */
public class MessageSender {

    private final String path = "SRC/Resources/messages_log.txt";

    /*** "Envía" un mensaje a un contacto: imprime y guarda en fichero.***/
    public void sendMessage(UserData contact, String mensaje) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String line = String.format("[%s] To: %s (%s) - %s", timestamp, contact.getNombre(), contact.getTelefono(), mensaje);

        // imprimir por consola (simulación)
        System.out.println("-> Enviando a " + contact.getNombre() + " (" + contact.getTelefono() + "): " + mensaje);

        // persistir
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error escribiendo messages_log.txt: " + e.getMessage());
        }
    }

    /*** "Llamada" simulada: solo imprime y persiste como llamada.***/
    public void callContact(UserData contact) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String line = String.format("[%s] LLAMADA a: %s (%s)", timestamp, contact.getNombre(), contact.getTelefono());
        System.out.println("-> Llamando a " + contact.getNombre() + " (" + contact.getTelefono() + ")...");
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error escribiendo messages_log.txt: " + e.getMessage());
        }
    }
}