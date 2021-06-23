package modelo.ambulancia;


import modelo.clinica.Operario;
import modelo.usuarios.Asociado;

import java.util.Observable;

public class Ambulancia extends Observable {
    protected IState estado;
    private static Ambulancia instance = null;
    private boolean isDisponibleAtencionDomicilio;
    private boolean isDisponibleTraslado;
    private boolean isDisponibleReparar;

    private Ambulancia() {
        this.estado = new DisponibleState(this);
    }


    public static Ambulancia get_instance() {
        if (instance == null)
            instance = new Ambulancia();
        return instance;
    }

    public void setDisponibleAtencionDomicilio(boolean disponibleAtencionDomicilio) {
        isDisponibleAtencionDomicilio = disponibleAtencionDomicilio;
    }

    public void setDisponibleTraslado(boolean disponibleTraslado) {
        isDisponibleTraslado = disponibleTraslado;
    }

    public void setDisponibleReparar(boolean disponibleReparar) {
        isDisponibleReparar = disponibleReparar;
    }

    protected void setEstado(IState estado) {
        this.estado = estado;
    }

    public synchronized void solicitaAtencionDomicilio(Asociado asociado) {
        while (!isDisponibleAtencionDomicilio) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaAtencionDomicilio();
        this.setChanged();
        this.notifyObservers(this.estado.toString()); //se notifica que la modelo.ambulancia fue solicitada
        notifyAll();
    }

    public synchronized void solicitaTraslado(Asociado asociado) {
        while (!isDisponibleTraslado) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaTraslado();
        this.setChanged();
        this.notifyObservers(this.estado.toString());
        notifyAll();
    }

    public synchronized void repararAmbulancia(Operario operario) {
        while (!isDisponibleReparar) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.repararAmbulancia(); // es la unica solicitud que puede realizar un operario
        this.setChanged();
        this.notifyObservers(this.estado.toString());
        notifyAll();
    }

    public synchronized void terminarUso() {
//        while (this.estado instanceof DisponibleState) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        this.estado.vuelveClinica();
        this.setChanged();
        this.notifyObservers(this.estado.toString()); //se notificia que la modelo.ambulancia esta disponible
        notifyAll();
    }
}
