package modelo.util;

public class Util {

    /**
     * Pone a esperar un hilo de ejecucion
     *
     * @param milisegundos
     */
    public static void espera(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
