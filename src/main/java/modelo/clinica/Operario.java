package modelo.clinica;

import modelo.ambulancia.Ambulancia;
import modelo.util.Util;

import java.util.Observable;

/**
 * Clase operario, clase utilzada para la simulacion. Representa a quien
 */
public class Operario extends Observable implements Runnable {
    public int getCantSolicitud() {
        return cantSolicitud;
    }

    private int cantSolicitud;
    private Ambulancia ambulancia;


    public Operario(Ambulancia ambulancia, int cantSolicitud) {
        this.ambulancia = ambulancia;
        this.cantSolicitud = cantSolicitud;
    }

    public void setCantSolicitud(int cantSolicitud) {
        this.cantSolicitud = cantSolicitud;
    }

    @Override
    public void run() {
        if (this.cantSolicitud != 0)
            for (int i = 0; i < this.cantSolicitud; i++) {
                this.setChanged();
                this.notifyObservers("intenta solicitar arreglo de Ambulancia");
                this.ambulancia.repararAmbulancia();
                Util.espera(2000);
                this.setChanged();
                this.notifyObservers("pudo solicitar arreglo de Ambulancia");
                this.ambulancia.terminarUso();
                Util.espera(2000);
                this.ambulancia.terminarUso();
                this.setChanged();
                this.notifyObservers("Termino de usar la ambulancia");

            }
    }
}
