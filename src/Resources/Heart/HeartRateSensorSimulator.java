package Resources.Heart;
import java.util.Random;

public class HeartRateSensorSimulator {

    private Random random = new Random();

    public int readHeartRate() {

        int chance = random.nextInt(10);

        if (chance == 0) {
            return 0; // simulamos pérdida de pulso
        }

        return 60 + random.nextInt(40); // rango normal
    }
}