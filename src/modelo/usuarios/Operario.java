package modelo.usuarios;

import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.DisponibleState;
import modelo.util.Util;

import java.util.Objects;
import java.util.Observable;
import java.util.Random;

public class Operario extends Observable implements Runnable {
    private String dni, nombre, domicilio, telefono;
    private int cantSolicitudes;
    private Ambulancia ambulancia;

    public Operario(String dni, String nombre, String domicilio, String telefono, int cantSolicitudes, Ambulancia ambulancia) {
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

    public void solicitaAmbulancia() {
        Random r = new Random();
        this.setChanged();
        if (r.nextBoolean()) {
            this.notifyObservers(this.nombre + " intenta solicitar atencion a Domicilio");
            this.ambulancia.solicitaAtencionDomicilio(this);

        } else {
            this.notifyObservers(this.nombre + " intenta solicitar atencion a Domicilio");
            this.ambulancia.solicitaTraslado(this);
        }
        Util.espera(1000);
        this.setChanged();
        this.notifyObservers(this.nombre + " solicito correctamente");

    }

    @Override
    public void run() {
        if (this.cantSolicitudes != 0)
            for (int i = 1; i <= this.cantSolicitudes; i++) {
                this.solicitaAmbulancia();
                this.ambulancia.terminarUso();
                this.ambulancia.terminarUso();
                this.setChanged();
                this.notifyObservers(this.nombre + " termino de usar la ambulancia");
            }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operario asociado = (Operario) o;
        return Objects.equals(dni, asociado.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return nombre;
    }


}
