package usuarios;

public abstract class Paciente  extends Usuarios{
    protected String RangoEtario;


    public Paciente(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica, String RangoEtario) {
        super(dni, domicilio, ciudad, telefono, nombre, numHistClinica);
        this.RangoEtario = RangoEtario;
    }


}
