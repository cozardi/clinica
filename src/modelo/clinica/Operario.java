package modelo.clinica;

import modelo.ambulancia.Ambulancia;

import java.util.Observable;

public class Operario extends Observable implements Runnable {
    private int cantSolicitud = 0;
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
            for (int i = 1; i <= this.cantSolicitud; i++) {
                this.setChanged();
                this.notifyObservers("intenta solicitar arreglo de Ambulancia");
                this.ambulancia.repararAmbulancia(this);
                this.setChanged();
                this.notifyObservers("pudo solicitar arreglo de Ambulancia");
                this.ambulancia.terminarUso();
                this.ambulancia.terminarUso();

            }


    }
}
