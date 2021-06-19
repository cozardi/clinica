package ambulancia;

import clinica.Operario;
import usuarios.Asociado;

public class Ambulancia {
    protected IState estado;
    private static Ambulancia instance = null;

    private Ambulancia() {
        this.estado = new DisponibleState(this);
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
                System.out.println("El Asociado" + asociado.getNombre()
                        + "solicito Atencion a domicilio pero la ambulancia esta ocupada");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitaAtencionDomicilio();
        // tiempo de simulacion
        this.estado.vuelveClinica();
        System.out.println("El Asociado" + asociado.getNombre() + "solicito Atencion a Domicilio correctamente");
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
        // tiempo de simulacion
        this.estado.vuelveClinica();
        System.out.println("El Asociado pidio la ambulancia");
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
        // tiempo de simulacion
        this.estado.vuelveClinica();
        System.out.println("El operario pidio una ambulancia");
        notifyAll();
    }
}
