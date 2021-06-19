package usuarios;

import ambulancia.Ambulancia;

import java.util.Objects;
import java.util.Random;

public class Asociado extends Thread {
    private String dni,nombre,domicilio,telefono;
    private int cantSolicitudes;
    private Ambulancia ambulancia;

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

    public void solicitaAmbulancia(){
        Random r = new Random();
        if (r.nextBoolean())
            this.ambulancia.solicitaAtencionDomicilio(this);
        else
            this.ambulancia.solicitaTraslado(this);
    }

    @Override
    public void run() {
        if (this.cantSolicitudes!=0)
            for (int i=1;i<=this.cantSolicitudes;i++)
                this.solicitaAmbulancia();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asociado asociado = (Asociado) o;
        return Objects.equals(dni, asociado.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
