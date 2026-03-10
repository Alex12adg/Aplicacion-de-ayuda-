package Resources.Voice;

import Resources.Emergency.EmergencyManager;

import java.util.Scanner;

public class VoiceDetector {

    private final VoiceConfig config;
    private boolean listening = false;

    public VoiceDetector(VoiceConfig config) {
        this.config = config;
    }

    public void startListening(EmergencyManager manager) {

        listening = true;

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MODO DETECCIÓN DE VOZ (SIMULADO) ===");
        System.out.println("Escribe la palabra clave para simular la activación.");
        System.out.println("Palabra actual: " + config.getKeyword());

        while (listening) {

            System.out.print("Entrada de voz simulada: ");

            String input = scanner.nextLine().toLowerCase();

            if (input.equals(config.getKeyword())) {

                System.out.println("Palabra clave detectada.");

                manager.triggerVoiceEmergency();
            }
        }
    }

    public void stop() {
        listening = false;
    }
}