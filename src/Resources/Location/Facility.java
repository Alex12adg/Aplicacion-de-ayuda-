package Resources.Location;

/*** Modelo para una instalación de auxilio.*/
public class Facility {
    private String tipo;    // hospital, comisaria, puesto_socorro, ambulancia ...
    private String nombre;
    private double lat;
    private double lon;
    private String info;

    public Facility(String tipo, String nombre, double lat, double lon, String info) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.info = info;
    }

    public String getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public double getLat() { return lat; }
    public double getLon() { return lon; }
    public String getInfo() { return info; }

    @Override
    public String toString() {
        return tipo + " | " + nombre + " (" + lat + ", " + lon + ") - " + info;
    }
}
