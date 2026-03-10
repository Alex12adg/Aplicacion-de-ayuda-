
package Resources.Contacts;

import Resources.User.UserData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*** Carga contactos de users.txt (formato: nombre,telefono,relacion) ***/
public class ContactManager {

    private final List<UserData> contacts = new ArrayList<>();
    private final String path = "SRC/Resources/users.txt";

    public ContactManager() {
        loadContacts();
    }

    private void loadContacts() {
        File f = new File(path);
        if (!f.exists()) {
            System.err.println("Warning: users.txt no encontrado en " + path);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] p = line.split(",");
                if (p.length < 2) continue;
                String nombre = p[0].trim();
                String telefono = p[1].trim();
                String relacion = p.length >= 3 ? p[2].trim() : "";
                contacts.add(new UserData(nombre, telefono, relacion));
            }
        } catch (Exception e) {
            System.err.println("Error leyendo users.txt: " + e.getMessage());
        }
    }

    public List<UserData> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    /*** Permite buscar contactos por relación (opcional).***/
    public List<UserData> getContactsByRelation(String relation) {
        List<UserData> r = new ArrayList<>();
        for (UserData u : contacts) {
            if (u.getRelacion() != null && u.getRelacion().equalsIgnoreCase(relation)) {
                r.add(u);
            }
        }
        return r;
    }
}