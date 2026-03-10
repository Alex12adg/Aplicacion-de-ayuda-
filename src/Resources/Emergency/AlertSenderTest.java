package Resources.Emergency;

import Resources.User.UserData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertSenderTest {

    @Test
    public void testAlertaTexto() {
        UserData u = new UserData("Test", "000");
        EmergencyEvent e = new EmergencyEvent("Prueba", "Ubicación X", u, 8);
        assertTrue(e.toString().contains("Prueba"));
    }
}
