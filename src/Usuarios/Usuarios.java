package Usuarios;

public abstract class Usuarios {
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
}
