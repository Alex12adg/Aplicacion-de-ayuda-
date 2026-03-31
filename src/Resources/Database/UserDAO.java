package Resources.Database;

import Resources.User.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    public UserData login(String input, String password) throws SQLException {

        String sql = """
            SELECT u.id, u.name, u.phone, u.role, u.email, u.password,
                   m.allergies, m.conditions, m.medications
            FROM users u
            LEFT JOIN medical_info m ON u.id = m.user_id
            WHERE (u.name = ? OR u.email = ?) AND u.password = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, input);
            stmt.setString(2, input);
            stmt.setString(3, password);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new UserData(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("role"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("allergies"),
                    rs.getString("conditions"),
                    rs.getString("medications")
            );
        }
    }

    public UserData registerUser(UserData user) throws SQLException {

        String sql = "INSERT INTO users (name, phone, role, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getPhone());
            stmt.setString(3, "user");
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());

            int rows = stmt.executeUpdate();

            if (rows <= 0) {
                return null;
            }

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("No se pudo obtener el id del usuario registrado");
                }

                return new UserData(
                        keys.getInt(1),
                        user.getNombre(),
                        user.getPhone(),
                        "user",
                        user.getEmail(),
                        user.getPassword(),
                        null,
                        null,
                        null
                );
            }
        }
    }
}
