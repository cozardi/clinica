package Usuarios;

/**
 * Interfaz que establece el juego de prioridades de asignación de la sala privada. <br>
 * Devuelve true en caso de que el objeto que la invoca tenga prioridad. <br>
 */

public interface PrioridadSala {

    /**
     * <b>Pre:</b> Tanto el objeto invocante como el parametro deben ser no nulos. <br>
     * @param p Objeto de tipo paciente. <br>
     * @return El valor de la invocacion al respectivo metodo que determina si
     * el objeto p tiene prioridad sobre el objeto actual en la Sala de Espera. <br>
     */
    boolean prioriza(PrioridadSala p);
    boolean beatsJoven();
    boolean beatsMayor();
    boolean beatsNino();

}
