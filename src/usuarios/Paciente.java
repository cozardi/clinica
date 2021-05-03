package usuarios;

public abstract class Paciente  extends Usuarios{
    protected int edad;


    public Paciente(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica,int edad) {
        super(dni, domicilio, ciudad, telefono, nombre, numHistClinica);
        this.edad = edad;
    }


}
