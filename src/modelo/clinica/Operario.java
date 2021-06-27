package modelo.clinica;

import modelo.ambulancia.Ambulancia;

import java.util.Observable;

/**
 * Clase operario, clase utilzada para la simulacion. Representa a quien es el encargado de reparar la ambulancia.
 */
public class Operario extends Observable implements Runnable {

    public int getCantSolicitud() {
        return cantSolicitud;
    }

    private int cantSolicitud;
    private Ambulancia ambulancia;

    /**
     * Constructor con dos parametros. Uno para la cantidad de solicitudes, otro para la doble referencia
     * con la ambulancia.
     * @param ambulancia Referencia a la ambulancia de la clinica.
     * @param cantSolicitud
     */
    public Operario(Ambulancia ambulancia, int cantSolicitud) {
        this.ambulancia = ambulancia;
        this.cantSolicitud = cantSolicitud;
    }

    public void setCantSolicitud(int cantSolicitud) {
        this.cantSolicitud = cantSolicitud;
    }

    /**
     * Metodo run sobreescrito para el uso del operario.
     * Se ejecutara el cuerpo siempre y cuando la cantidad de solicitudes sea distinta de 0. <br>
     *
     */
    @Override
    public void run() {
        if (this.cantSolicitud != 0)
            for (int i = 0; i < this.cantSolicitud; i++) {
                this.setChanged();
                this.notifyObservers("intenta solicitar arreglo de Ambulancia");
                this.ambulancia.repararAmbulancia(this);
                this.setChanged();
                this.notifyObservers("pudo solicitar arreglo de Ambulancia");
                this.ambulancia.terminarUso();
                this.ambulancia.terminarUso();
                this.setChanged();
                this.notifyObservers("Termino de usar la ambulancia");

            }
    }
}
