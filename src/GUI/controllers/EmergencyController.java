package GUI.controllers;

import Resources.Emergency.EmergencyManager;
import javafx.fxml.FXML;

public class EmergencyController {

    private EmergencyManager manager;

    public void setEmergencyManager(EmergencyManager manager) {
        this.manager = manager;
    }

    @FXML
    private void handleStartEmergency() {

        Thread thread = new Thread(() -> {
            manager.startSystemInteractive();
        });

        thread.setDaemon(true);
        thread.start();
    }
}
