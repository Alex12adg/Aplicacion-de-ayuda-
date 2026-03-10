package Resources.Heart;

public class HeartRateMonitor {

    private HeartRateSensorSimulator sensor;
    private boolean monitoring = false;
    private int noPulseCounter = 0;
    public HeartRateMonitor() {

        sensor = new HeartRateSensorSimulator();
    }

    public void startMonitoring() {

        monitoring = true;
        System.out.println("=== MONITOR DE PULSACIONES ACTIVADO ===");
        while (monitoring) {

            int heartRate = sensor.readHeartRate();
            System.out.println("Frecuencia cardíaca detectada: " + heartRate);
            if (heartRate == 0) {

                noPulseCounter++;

            } else {

                noPulseCounter = 0;
            }

            if (noPulseCounter >= 3) {

                triggerPreAlert();
                break;
            }

            try {

                Thread.sleep(3000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    private void triggerPreAlert() {

        System.out.println("POSIBLE AUSENCIA DE PULSO DETECTADA");
        System.out.println("Iniciando alarma preventiva...");

        HeartRateAlert alert = new HeartRateAlert();

        boolean userConfirmed = alert.waitForUserConfirmation();

        if (userConfirmed) {

            System.out.println("El usuario ha confirmado que está bien.");

        } else {

            System.out.println("No hubo respuesta del usuario.");
            System.out.println("En la siguiente fase se enviará una emergencia.");
        }
    }
}
