package modelo.clinica;

import modelo.ambulancia.Ambulancia;

public class Operario extends Thread {
    private int cantSolicitud=0;
    private Ambulancia ambulancia;


    public Operario(Ambulancia ambulancia,int cantSolicitud) {
        this.ambulancia = ambulancia;
        this.cantSolicitud = cantSolicitud;
    }

    public void setCantSolicitud(int cantSolicitud) {
        this.cantSolicitud = cantSolicitud;
    }

    @Override
    public void run() {
        if (this.cantSolicitud!=0)
            for(int i=1;i<=this.cantSolicitud;i++)
                this.ambulancia.repararAmbulancia(this);
    }
}
