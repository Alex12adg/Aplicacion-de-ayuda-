package Services;

import Resources.Database.UserDAO;
import Resources.User.UserData;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public UserData login(String input, String password) throws Exception {

        if (input == null || input.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("Campos obligatorios vacios");
        }

        UserData user = userDAO.login(input, password);

        if (user == null) {
            throw new Exception("Usuario o contrasena incorrectos");
        }

        return user;
    }

    public UserData register(String nombre, String phone, String email, String password) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre obligatorio");
        }

        if (email == null || email.isEmpty()) {
            throw new Exception("Email obligatorio");
        }

        if (password == null || password.length() < 4) {
            throw new Exception("Contrasena demasiado corta");
        }

        UserData user = new UserData(nombre, phone, email, password);
        UserData createdUser = userDAO.registerUser(user);

        if (createdUser == null) {
            throw new Exception("Error al registrar usuario");
        }

        return createdUser;
    }
}
