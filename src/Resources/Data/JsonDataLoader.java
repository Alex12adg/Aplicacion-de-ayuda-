package Resources.Data;

import Resources.Emergency.EmergencyCenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonDataLoader {

    public static List<EmergencyCenter> loadCenters(String path) {
        List<EmergencyCenter> centers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String name = "", type = "";
            double lat = 0, lon = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.contains("\"name\"")) {
                    name = extractString(line);
                }
                if (line.contains("\"type\"")) {
                    type = extractString(line);
                }
                if (line.contains("\"latitude\"")) {
                    lat = extractDouble(line);
                }
                if (line.contains("\"longitude\"")) {
                    lon = extractDouble(line);
                }

                if (!name.isEmpty() && !type.isEmpty() && lat != 0 && lon != 0) {
                    centers.add(new EmergencyCenter(name, type, lat, lon));
                    name = ""; type = ""; lat = 0; lon = 0;
                }
            }

        } catch (Exception e) {
            System.out.println("Error leyendo JSON: " + e.getMessage());
        }

        return centers;
    }

    private static String extractString(String line) {
        return line.split(":")[1].replace("\"", "").replace(",", "").trim();
    }

    private static double extractDouble(String line) {
        return Double.parseDouble(line.split(":")[1].replace(",", "").trim());
    }
}