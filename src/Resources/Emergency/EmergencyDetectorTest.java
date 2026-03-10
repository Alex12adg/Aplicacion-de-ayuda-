package Resources.Emergency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmergencyDetectorTest {

    @Test
    public void testGravedadValida() {
        EmergencyDetector d = new EmergencyDetector(5);
        assertTrue(invoke(d, 7));
    }

    @Test
    public void testGravedadInvalida() {
        EmergencyDetector d = new EmergencyDetector(5);
        assertFalse(invoke(d, 3));
    }

    private boolean invoke(EmergencyDetector detector, int gravedad) {
        try {
            var m = detector.getClass().getDeclaredMethod("validateSeverity", int.class);
            m.setAccessible(true);
            return (boolean) m.invoke(detector, gravedad);
        } catch (Exception e) {
            return false;
        }
    }
}