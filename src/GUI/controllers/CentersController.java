package GUI.controllers;

import Resources.Emergency.EmergencyCenter;
import Resources.Emergency.EmergencyManager;
import Resources.Location.FacilityLocator;
import Resources.Location.GPSModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class CentersController {

    private final ObservableList<String> centerItems = FXCollections.observableArrayList();
    private final GPSModule gpsModule = new GPSModule();
    private EmergencyManager emergencyManager;

    @FXML
    private CheckBox automaticLocationCheck;

    @FXML
    private VBox manualLocationBox;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField radiusField;

    @FXML
    private Label centersStatusLabel;

    @FXML
    private Label searchSummaryLabel;

    @FXML
    private ListView<String> centersList;

    public void setEmergencyManager(EmergencyManager emergencyManager) {
        this.emergencyManager = emergencyManager;
        loadAllCenters();
    }

    @FXML
    public void initialize() {
        centersList.setItems(centerItems);
        automaticLocationCheck.setSelected(true);
        updateLocationMode();
        centersStatusLabel.setText("Consulta centros cercanos con ubicacion automatica o manual.");
        searchSummaryLabel.setText("Aun no se ha ejecutado ninguna busqueda.");
    }

    @FXML
    private void updateLocationMode() {
        boolean automatic = automaticLocationCheck.isSelected();
        manualLocationBox.setManaged(!automatic);
        manualLocationBox.setVisible(!automatic);
        latitudeField.setDisable(automatic);
        longitudeField.setDisable(automatic);

        if (automatic) {
            latitudeField.clear();
            longitudeField.clear();
        }
    }

    @FXML
    private void handleFindCenters() {
        if (emergencyManager == null) {
            centersStatusLabel.setText("El sistema de centros no esta disponible.");
            return;
        }

        try {
            double radius = Double.parseDouble(radiusField.getText().trim());
            double[] coordinates = resolveCoordinates();
            List<EmergencyCenter> allCenters = emergencyManager.loadCenters();
            FacilityLocator locator = new FacilityLocator(allCenters);
            List<EmergencyCenter> nearby = locator.findNearby(coordinates[0], coordinates[1], radius);

            centerItems.clear();

            for (EmergencyCenter center : nearby) {
                centerItems.add(formatCenter(center, coordinates[0], coordinates[1]));
            }

            if (centerItems.isEmpty()) {
                centerItems.add("No se han encontrado centros dentro del radio indicado.");
            }

            centersStatusLabel.setText("Busqueda completada.");
            searchSummaryLabel.setText(
                    "Ubicacion: " + String.format("%.5f, %.5f", coordinates[0], coordinates[1])
                            + " | Radio: " + radius + " km | Resultados: " + nearby.size()
            );
        } catch (NumberFormatException e) {
            centersStatusLabel.setText("El radio y las coordenadas deben ser numericos.");
        } catch (IllegalArgumentException e) {
            centersStatusLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void handleShowAllCenters() {
        loadAllCenters();
        searchSummaryLabel.setText("Listado completo de centros cargados en la aplicacion.");
        centersStatusLabel.setText("Mostrando todos los centros disponibles.");
    }

    private void loadAllCenters() {
        if (emergencyManager == null || centersList == null) {
            return;
        }

        centerItems.clear();

        for (EmergencyCenter center : emergencyManager.loadCenters()) {
            centerItems.add(center.getType() + " | " + center.getName() + " | " + center.getLatitude() + ", " + center.getLongitude());
        }

        if (centerItems.isEmpty()) {
            centerItems.add("No hay centros cargados.");
        }
    }

    private double[] resolveCoordinates() {
        if (automaticLocationCheck.isSelected()) {
            return GPSModule.parseLatLon(gpsModule.getAutoLocation());
        }

        String latitude = latitudeField.getText();
        String longitude = longitudeField.getText();

        if (latitude == null || latitude.isBlank() || longitude == null || longitude.isBlank()) {
            throw new IllegalArgumentException("Debes indicar latitud y longitud manuales.");
        }

        return GPSModule.parseLatLon(latitude.trim() + "," + longitude.trim());
    }

    private String formatCenter(EmergencyCenter center, double lat, double lon) {
        double distance = calculateDistance(lat, lon, center.getLatitude(), center.getLongitude());
        return center.getType() + " | " + center.getName() + " | Distancia aprox: " + String.format("%.2f km", distance);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double earthRadiusKm = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * c;
    }
}
