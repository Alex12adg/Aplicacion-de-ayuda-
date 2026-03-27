package GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MedicalController {

    @FXML
    private TextArea medicalInfo;

    @FXML
    public void initialize() {

        // Datos simulados (luego los conectaremos a UserData)
        medicalInfo.setText(
                "=== HISTORIAL MÉDICO ===\n\n" +
                        "Alergias:\n - Polen\n - Penicilina\n\n" +
                        "Dietas:\n - Baja en sodio\n\n" +
                        "Condiciones:\n - Hipertensión\n\n" +
                        "Medicamentos:\n - Enalapril\n"
        );
    }
}