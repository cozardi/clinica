package persistencia;

import java.io.Serializable;

/**
 * 
 * Clase con los datos a serializar de un Asociado
 *
 */
public class AsociadoDTO implements Serializable {

    private String dni, nombre, domicilio, telefono;
    private int cantSolicitudes;

    public AsociadoDTO() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCantSolicitudes() {
        return cantSolicitudes;
    }

    public void setCantSolicitudes(int cantSolicitudes) {
        this.cantSolicitudes = cantSolicitudes;
    }


}
