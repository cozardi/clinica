package usuarios;

import java.util.Objects;

/**
 * Define tipo de Usuario que pasa por la clinica (Medico y Paciente)
 */
public abstract class Usuarios implements Comparable<Usuarios>{
    protected String dni,domicilio,ciudad,telefono,nombre;
    protected int numero;

    public Usuarios(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero) {
        this.dni = dni;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getDni() {
        return dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return numero == usuarios.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

	public int getNumero() {
		return numero;
	}

    @Override
    public int compareTo(Usuarios o) {
        return this.numero - o.numero;
    }
}
