package ambulancia;

import clinica.Operario;
import usuarios.Asociado;

import java.util.Observable;

public class Ambulancia extends Observable {
    protected IState estado;
    private static Ambulancia instance = null;

    private Ambulancia() {
        this.estado = new DisponibleState(this);
        this.setChanged();
        this.notifyObservers("Ambulancia Disponible");
    }

    public static Ambulancia get_instance() {
        if (instance != null)
            return instance;
        else
            return new Ambulancia();
    }

    protected void setEstado(IState estado) {
        this.estado = estado;
    }

    public synchronized void solicitaAtencionDomicilio(Asociado asociado) {
        while (!(this.estado instanceof DisponibleState || estado instanceof RegresandoClinicaState)) {
            try {
                System.out.println("El Asociado" + asociado.getNombre() + "solicito Atencion a domicilio pero la ambulancia esta ocupada");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaAtencionDomicilio();

        this.setChanged();
        this.notifyObservers(this.estado.toString()); //se notifica que la ambulancia fue solicitada
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.estado.vuelveClinica();

        this.setChanged();
        this.notifyObservers("Ambulancia Disponible"); //se notificia que la ambulancia esta disponible
        notifyAll();
    }

    public synchronized void solicitaTraslado(Asociado asociado) {
        while (!(this.estado instanceof DisponibleState)) {
            try {
                System.out.println("La Ambulancia esta ocupada");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaTraslado();
        this.setChanged();
        this.notifyObservers(this.estado.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.estado.vuelveClinica();
        this.setChanged();
        this.notifyObservers("Ambulancia Disponible");
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.estado.vuelveClinica();

        this.setChanged();
        this.notifyObservers("Ambulancia Disponible");
        notifyAll();
    }
}
