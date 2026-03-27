package GUI.controllers;

import Resources.Danger.DangerAlertSystem;
import Resources.Emergency.EmergencyManager;
import javafx.fxml.FXML;

public class DangerController {

    private DangerAlertSystem system;
    private EmergencyManager manager;

    public void setDangerSystem(DangerAlertSystem system, EmergencyManager manager) {
        this.system = system;
        this.manager = manager;
    }

    @FXML
    private void handleDangerAlert() {

        Thread thread = new Thread(() -> {
            system.activateAlert(manager);
        });

        thread.setDaemon(true);
        thread.start();
    }
}
