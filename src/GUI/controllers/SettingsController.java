package GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SettingsController {

    @FXML
    private Label settingsStatusLabel;

    @FXML
    public void initialize() {
        settingsStatusLabel.setText("Esta pantalla queda preparada para futuras opciones de la aplicacion.");
    }
}
