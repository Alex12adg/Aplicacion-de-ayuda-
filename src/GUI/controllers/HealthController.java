package GUI.controllers;

import Resources.Heart.HeartRateMonitor;
import javafx.fxml.FXML;

public class HealthController {

    private HeartRateMonitor monitor;

    public void setHeartMonitor(HeartRateMonitor monitor) {
        this.monitor = monitor;
    }

    @FXML
    private void handleStartMonitoring() {

        Thread thread = new Thread(() -> {
            monitor.startMonitoring();
        });

        thread.setDaemon(true);
        thread.start();
    }
}
