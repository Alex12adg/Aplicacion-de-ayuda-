package Resources.Location;

import Resources.Emergency.EmergencyCenter;

import java.util.ArrayList;
import java.util.List;

public class FacilityLocator {

    private final List<EmergencyCenter> allCenters;

    public FacilityLocator(List<EmergencyCenter> centers) {
        this.allCenters = centers;
    }

    /** Busca centros dentro de un radio (km) */
    public List<EmergencyCenter> findNearby(double lat, double lon, double radiusKm) {

        List<EmergencyCenter> result = new ArrayList<>();

        for (EmergencyCenter center : allCenters) {

            double dist = haversine(lat, lon, center.getLatitude(), center.getLongitude());

            if (dist <= radiusKm) {
                result.add(center);
            }
        }

        return result;
    }

    /** Fórmula Haversine */
    private double haversine(double lat1, double lon1, double lat2, double lon2) {

        final double R = 6371.0;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(Math.toRadians(lat1)) *
                                Math.cos(Math.toRadians(lat2)) *
                                Math.sin(dLon / 2) *
                                Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}