package persistencia;

import java.io.Serializable;

/**
 * 
 * Clase con los datos a serializar de un Operario de ambulancia
 *
 */
public class OperarioDTO implements Serializable {


    private int cantSolicitudes;

    public OperarioDTO() {
    }

    public int getCantSolicitudes() {
        return cantSolicitudes;
    }

    public void setCantSolicitudes(int cantSolicitudes) {
        this.cantSolicitudes = cantSolicitudes;
    }


}
