package utilidad;

public class Utilidad {

    public static void showMessagePredefined() {
        System.out.println("---------------------------------------");
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void stopAndContinue() {
        timeToWait();
        cleanScreen();
    }

    public static void cleanScreen() {
        for (int i = 0; i < 10; i++) {
            Utilidad.showMessage("");
        }
        System.out.flush();
    }

    public static void timeToWait() {
        int timeWait = 10; // Segundos
        try {
            for (int i = 0; i < timeWait; i++) {
                Thread.sleep(150);
            }
        } catch (InterruptedException ie) {
            showMessage("Tiempo de espera interrumpido");
        }
    }
}
