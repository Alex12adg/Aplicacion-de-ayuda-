package Resources.User;

public class UserData {
    private String nombre;
    private String telefono;
    private String relacion; // opcional (hermano, vecino, servicio...)

    public UserData(String nombre, String telefono) {
        this(nombre, telefono, "");
    }

    public UserData(String nombre, String telefono, String relacion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.relacion = relacion;
    }

    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getRelacion() { return relacion; }
}