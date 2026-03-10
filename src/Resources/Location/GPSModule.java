package Resources.Location;

import java.util.Random;
import java.util.Scanner;

/*** Módulo GPS simulado.
 * - getAutoLocation() devuelve "lat,lon"
 * - getManualLocation() pide lat y lon por consola
 ***/
public class GPSModule {

    private final Random rand = new Random();

    /*** Genera coordenadas simuladas */
    public String getAutoLocation() {
        double lat = 40.30 + (rand.nextDouble() * 0.3);   // 40.30 .. 40.60
        double lon = -3.80 + (rand.nextDouble() * 0.3);   // -3.80 .. -3.50
        return String.format("%.6f,%.6f", lat, lon);
    }

    /*** Pide latitud/longitud al usuario.***/
    public String getManualLocation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce latitud (ej. 40.42030): ");
        String lat = sc.nextLine().trim();
        System.out.print("Introduce longitud (ej. -3.70577): ");
        String lon = sc.nextLine().trim();
        return lat + "," + lon;
    }

    /*** Método principal que decide automático/manual.***/
    public String getLocation(boolean automatico) {
        if (automatico) return getAutoLocation();
        else return getManualLocation();
    }

    /*** Parsea "lat,lon" y devuelve un array [lat, lon] ***/
    public static double[] parseLatLon(String s) throws IllegalArgumentException {
        if (s == null) throw new IllegalArgumentException("ubicación nula");
        String[] parts = s.split(",");
        if (parts.length != 2) throw new IllegalArgumentException("Formato inválido de lat,lon");
        try {
            double lat = Double.parseDouble(parts[0].trim());
            double lon = Double.parseDouble(parts[1].trim());
            return new double[]{lat, lon};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Coordenadas no numéricas");
        }
    }
}