package GUI.controllers;

import Resources.Emergency.EmergencyManager;
import Resources.Voice.VoiceDetector;
import Resources.Voice.VoiceConfig;
import Resources.Danger.DangerAlertSystem;
import Resources.Heart.HeartRateMonitor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MainController {

    // =========================
    // ELEMENTOS GUI
    // =========================

    @FXML
    private Label usernameLabel;

    @FXML
    private StackPane contentArea;

    // =========================
    // SISTEMAS BACKEND
    // =========================

    private EmergencyManager emergencyManager;
    private VoiceDetector voiceDetector;
    private VoiceConfig voiceConfig;
    private DangerAlertSystem dangerSystem;
    private HeartRateMonitor heartMonitor;

    // =========================
    // CONSTRUCTOR
    // =========================

    public MainController() {

        emergencyManager = new EmergencyManager();

        voiceConfig = new VoiceConfig();
        voiceDetector = new VoiceDetector(voiceConfig);

        dangerSystem = new DangerAlertSystem();
        heartMonitor = new HeartRateMonitor();
    }

    // =========================
    // INICIALIZACIÓN
    // =========================

    @FXML
    public void initialize() {

        usernameLabel.setText("Usuario Demo");

        // Cargar vista inicial
        loadView("home-view.fxml");
    }

    // =========================
    // MÉTODO CENTRAL DE CARGA
    // =========================

    private void loadView(String fxml) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/GUI/views/" + fxml)
            );

            Parent view = loader.load();

            // Obtener controller de la vista
            Object controller = loader.getController();

            // Inyectar dependencias si aplica
            if (controller instanceof EmergencyController) {
                ((EmergencyController) controller).setEmergencyManager(emergencyManager);
            }

            if (controller instanceof VoiceController) {
                ((VoiceController) controller).setVoiceDetector(voiceDetector, emergencyManager);
            }

            if (controller instanceof DangerController) {
                ((DangerController) controller).setDangerSystem(dangerSystem, emergencyManager);
            }

            if (controller instanceof HealthController) {
                ((HealthController) controller).setHeartMonitor(heartMonitor);
            }

            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);

        } catch (Exception e) {
            System.out.println("Error cargando vista: " + fxml);
            e.printStackTrace();
        }
    }

    // =========================
    // NAVEGACIÓN (MENÚ)
    // =========================

    @FXML
    private void loadHome() {
        loadView("Home-view.fxml");
    }

    @FXML
    private void loadEmergency() {
        loadView("Emergency-view.fxml");
    }

    @FXML
    private void loadHealth() {
        loadView("Health-view.fxml");
    }

    @FXML
    private void loadVoice() {
        loadView("Voice-view.fxml");
    }

    @FXML
    private void loadDanger() {
        loadView("Danger-view.fxml");
    }

    @FXML
    private void loadCenters() {
        loadView("Centers-view.fxml");
    }

    @FXML
    private void loadMedical() {
        loadView("medical-view.fxml");
    }

    // =========================
    // FUNCIONALIDADES BACKEND
    // =========================

    @FXML
    private void handleEmergency() {

        Thread thread = new Thread(() -> {
            emergencyManager.startSystemInteractive();
        });

        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleVoiceDetection() {

        Thread thread = new Thread(() -> {
            voiceDetector.startListening(emergencyManager);
        });

        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleDangerAlert() {

        Thread thread = new Thread(() -> {
            dangerSystem.activateAlert(emergencyManager);
        });

        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleHeartMonitor() {

        Thread thread = new Thread(() -> {
            heartMonitor.startMonitoring(); // sin parámetros
        });

        thread.setDaemon(true);
        thread.start();
    }
}