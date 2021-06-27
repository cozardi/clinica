package modelo.ambulancia;


import modelo.clinica.Operario;
import modelo.usuarios.Asociado;
import vista.Recursos;

import java.util.Observable;

/**
 * Clase utilizada como monitor y recurso compartido por los asociados
 */
public class Ambulancia extends Observable {
    protected IState estado;
    private static Ambulancia instance = null;
    private boolean isDisponibleAtencionDomicilio;
    private boolean isDisponibleTraslado;
    private boolean isDisponibleReparar;

    /**
     * Constructor que setea en disponible el estado de la ambulancia
     */
    private Ambulancia() {
        this.estado = new DisponibleState(this);
    }

    /**
     * Devuelve una instancia de la ambulancia. Si no existe la crea <br>
     * Si existe devuelve la instancia existente
     *
     * @return Ambulancia
     */
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

    /**
     * PRE: si la ambulancia no esta disponible para la atencion a domicilio el hilo quedara durmiendo <br>
     * Metodo sincronizado que cambia el estado de la ambulancia
     * Simula que la ambulancia esta yendo a atender a domicilio a un Asociado
     * Delega el trabajo a su atributo IState estado
     */
    public synchronized void solicitaAtencionDomicilio() {
        while (!isDisponibleAtencionDomicilio) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaAtencionDomicilio();
        this.setChanged();
        this.notifyObservers(this.estado.getFoto());
        notifyAll();
    }

    /**
     * PRE: si la ambulancia no esta disponible para el traslado el hilo quedara durmiendo <br>
     * Metodo sincronizado que cambia el estado de la ambulancia
     * Simula que la ambulancia esta trasladando un paciente a la clinica
     * Delega el trabajo a su atributo IState estado
     */
    public synchronized void solicitaTraslado() {
        while (!isDisponibleTraslado) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaTraslado();
        this.setChanged();
        this.notifyObservers(this.estado.getFoto());
        notifyAll();
    }

    /**
     * PRE: si la ambulancano esta disponible para ser reparada el hilo quedara durmiendo <br>
     * Metodo sincronizado que cambia el estado de la ambulancia
     * Simula que la ambulancia esta siendo reparada por el operario
     * Delega el trabajo a su atributo IState estado
     */
    public synchronized void repararAmbulancia() {
        while (!isDisponibleReparar) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.repararAmbulancia(); // es la unica solicitud que puede realizar un operario
        this.setChanged();
        this.notifyObservers(this.estado.getFoto());
        notifyAll();
    }

    /**
     * Metodo sincronizado que cambia el estado de la ambulancia
     * Simula que la ambulancia termino de hacer la accion que estaba haciendo
     * Delega el trabajo a su atributo IState estado
     */
    public synchronized void terminarUso() {
        this.estado.vuelveClinica();
        this.setChanged();
        this.notifyObservers(this.estado.getFoto()); //se notificia que la modelo.ambulancia esta disponible
        notifyAll();
    }
}
