package GUI.controllers;

import Resources.Danger.DangerAlertSystem;
import Resources.Emergency.EmergencyManager;
import Resources.Heart.HeartRateMonitor;
import Resources.Session.UserSession;
import Resources.User.UserData;
import Resources.Voice.VoiceConfig;
import Resources.Voice.VoiceDetector;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

    private static final double MENU_EXPANDED_WIDTH = 200;
    private static final double MENU_COLLAPSED_WIDTH = 0;

    @FXML
    private Label usernameLabel;

    @FXML
    private StackPane contentArea;

    @FXML
    private VBox sideMenu;

    private EmergencyManager emergencyManager;
    private VoiceDetector voiceDetector;
    private VoiceConfig voiceConfig;
    private DangerAlertSystem dangerSystem;
    private HeartRateMonitor heartMonitor;
    private boolean menuVisible = true;

    public MainController() {
        emergencyManager = new EmergencyManager();
        voiceConfig = new VoiceConfig();
        voiceDetector = new VoiceDetector(voiceConfig);
        dangerSystem = new DangerAlertSystem();
        heartMonitor = new HeartRateMonitor();
    }

    @FXML
    public void initialize() {
        UserData user = UserSession.getUser();
        usernameLabel.setText(user != null ? user.getNombre() : "Usuario Demo");
        loadView("Home-view.fxml");
        applyMenuWidth(MENU_EXPANDED_WIDTH);
    }

    private void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/GUI/Views/" + fxml)
            );

            Parent view = loader.load();
            Object controller = loader.getController();

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

    @FXML
    private void toggleSideMenu() {
        double targetWidth = menuVisible ? MENU_COLLAPSED_WIDTH : MENU_EXPANDED_WIDTH;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(220),
                        new KeyValue(sideMenu.prefWidthProperty(), targetWidth),
                        new KeyValue(sideMenu.minWidthProperty(), targetWidth),
                        new KeyValue(sideMenu.maxWidthProperty(), targetWidth),
                        new KeyValue(sideMenu.opacityProperty(), menuVisible ? 0 : 1)
                )
        );
        timeline.play();
        menuVisible = !menuVisible;
    }

    private void applyMenuWidth(double width) {
        sideMenu.setPrefWidth(width);
        sideMenu.setMinWidth(width);
        sideMenu.setMaxWidth(width);
        sideMenu.setOpacity(width == 0 ? 0 : 1);
    }

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
        loadView("health-view.fxml");
    }

    @FXML
    private void loadVoice() {
        loadView("voice-view.fxml");
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
        loadView("Medical-form-view.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            UserSession.clear();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/GUI/Views/Login-view.fxml")
            );

            Parent root = loader.load();
            Stage stage = (Stage) usernameLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 500));
            stage.show();
        } catch (Exception e) {
            System.out.println("Error al cerrar sesion");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEmergency() {
        Thread thread = new Thread(() -> emergencyManager.startSystemInteractive());
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleVoiceDetection() {
        Thread thread = new Thread(() -> voiceDetector.startListening(emergencyManager));
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleDangerAlert() {
        Thread thread = new Thread(() -> dangerSystem.activateAlert(emergencyManager));
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleHeartMonitor() {
        Thread thread = new Thread(() -> heartMonitor.startMonitoring());
        thread.setDaemon(true);
        thread.start();
    }
}
