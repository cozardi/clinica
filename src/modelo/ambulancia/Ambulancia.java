package modelo.ambulancia;

import modelo.clinica.Operario;
import modelo.usuarios.Asociado;
import modelo.util.Util;

import java.util.Observable;

public class Ambulancia extends Observable {
    protected IState estado;
    private static Ambulancia instance = null;

    private Ambulancia() {
        this.estado = new DisponibleState(this);
    }

    public static Ambulancia get_instance() {
        if (instance == null)
            instance = new Ambulancia();
        return instance;
    }

    protected void setEstado(IState estado) {
        this.estado = estado;
    }

    public synchronized void solicitaAtencionDomicilio(Asociado asociado) {
        while (!(this.estado instanceof DisponibleState || estado instanceof RegresandoClinicaState)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaAtencionDomicilio();
        this.setChanged();
        this.notifyObservers(this.estado.toString()); //se notifica que la modelo.ambulancia fue solicitada
        Util.espera(1000);
        this.estado.vuelveClinica();
        if (!(estado instanceof DisponibleState)){
            Util.espera(1000);
            this.estado.vuelveClinica();
        }
        this.setChanged();
        this.notifyObservers(this.estado.toString()); //se notificia que la modelo.ambulancia esta disponible
        notifyAll();
    }

    public synchronized void solicitaTraslado(Asociado asociado) {
        while (!(this.estado instanceof DisponibleState)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaTraslado();
        this.setChanged();
        this.notifyObservers(this.estado.toString());
        Util.espera(1000);
        this.estado.vuelveClinica();
        this.setChanged();
        this.notifyObservers(this.estado.toString());
        notifyAll();
    }

    public synchronized void repararAmbulancia(Operario operario) {
        while (!(this.estado instanceof DisponibleState || this.estado instanceof enTallerState)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.repararAmbulancia(); // es la unica solicitud que puede realizar un operario
        this.setChanged();
        this.notifyObservers(this.estado.toString());
        Util.espera(1000);
        this.estado.vuelveClinica();
        if (!(estado instanceof DisponibleState)){
            Util.espera(1000);
            this.estado.vuelveClinica();
        }
        this.setChanged();
        this.notifyObservers(this.estado.toString());
        notifyAll();
    }
}
