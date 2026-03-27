package GUI.controllers;

import Resources.Voice.VoiceDetector;
import Resources.Emergency.EmergencyManager;
import javafx.fxml.FXML;

public class VoiceController {

    private VoiceDetector detector;
    private EmergencyManager manager;

    public void setVoiceDetector(VoiceDetector detector, EmergencyManager manager) {
        this.detector = detector;
        this.manager = manager;
    }

    @FXML
    private void handleStartVoice() {

        Thread thread = new Thread(() -> {
            detector.startListening(manager);
        });

        thread.setDaemon(true);
        thread.start();
    }
}
