package modelo.usuarios;

import modelo.ambulancia.Ambulancia;
import modelo.util.Util;

import java.util.Objects;
import java.util.Observable;
import java.util.Random;

/**
 * Clase de los asociados de la clinica.
 */
public class Asociado extends Observable implements Runnable {
    private String dni, nombre, domicilio, telefono;
    private int cantSolicitudes;
    private Ambulancia ambulancia;

    /**
     * Constructor con los parametros del asociado requeridos, y una referencia a la ambulancia de la clinica.
     * @param dni
     * @param nombre
     * @param domicilio
     * @param telefono
     * @param cantSolicitudes
     * @param ambulancia
     */
    public Asociado(String dni, String nombre, String domicilio, String telefono, int cantSolicitudes, Ambulancia ambulancia) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.cantSolicitudes = cantSolicitudes;
        this.ambulancia = ambulancia;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getCantSolicitudes() {
        return cantSolicitudes;
    }

    /**
     * Metodo que permite a un asociado solicitar la ambulancia, e ingresar en el metodo synchronized correspondiente.
     * Notifica al observador (PanelAsociado) lo que esta intentando hacer. <br>
     * Mediante la funcion random es que funciona la simulacion para que se le asigne una tarea.
     *
     */

    public void solicitaAmbulancia() {
        Random r = new Random();
        this.setChanged();
        if (r.nextBoolean()) {
            this.notifyObservers("intenta solicitar atencion a Domicilio");
            this.ambulancia.solicitaAtencionDomicilio();

        } else {
            this.notifyObservers("intenta solicitar traslado a Clinica");
            this.ambulancia.solicitaTraslado();
        }
        Util.espera(2000);
        this.setChanged();
        this.notifyObservers("solicito correctamente");

    }

    /**
     * Metodo run sobreescrito para que se inicie la ejecucion dle hilo del asociado.
     * Se ejecutara el cuerpo siempre y cuando la cantidad de solicitudes sea distinta de 0.
     * <b>Post: </b> Notifica al observador los cambios.
     */
    @Override
    public void run() {
        if (this.cantSolicitudes != 0)
            for (int i = 1; i <= this.cantSolicitudes; i++) {
                this.solicitaAmbulancia();
                this.ambulancia.terminarUso();
                Util.espera(2000);
                this.ambulancia.terminarUso();
                this.setChanged();
                this.notifyObservers("termino de usar la ambulancia");
            }
    }

    /**
     * Metodo equals redefinido para comparar dos asociados. El criterio de igualdad es el dni.
     * @param o objeto que puede o no ser un asociado.
     * @return 1 si ambos asociados tienen el mismo dni, otro valor si no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asociado asociado = (Asociado) o;
        return Objects.equals(dni, asociado.dni);
    }

    /**
     * Metodo hash redefinido. Dos asociados con el mismo dni son iguales (es decir, seran los dos el mismo asociado),
     * por lo tanto su hash debe cincidir.
     * @return el hashcode del asociado basado en el dni.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return nombre;
    }


}
