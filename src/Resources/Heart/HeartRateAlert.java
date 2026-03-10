package Resources.Heart;
import java.util.Scanner;

public class HeartRateAlert {

    public boolean waitForUserConfirmation() {

        Scanner sc = new Scanner(System.in);

        System.out.println("ALERTA: posible problema cardíaco.");
        System.out.println("Escribe 'ok' en 20 segundos para cancelar.");

        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 20000) {

            if (sc.hasNextLine()) {

                String response = sc.nextLine();

                if (response.equalsIgnoreCase("ok")) {

                    System.out.println("Alerta cancelada por el usuario.");
                    return true;
                }
            }
        }

        System.out.println("No se recibió respuesta del usuario.");

        return false;
    }
}
