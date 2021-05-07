package usuarios;


/**
 * Es el medico base sin ningun decorator aplicado (esta clase <b>no</b> deberia instanciarse en ninguna situacion).
 */
public class Medico extends Usuarios implements IMedico{
    private double Honorario;


    /**
     * Constructor de Medico base
     * @param dni Contiene el DNI del medico
     * @param domicilio contiene el domicilio del medico
     * @param ciudad representa la ciudad de origen
     * @param telefono contiene el telefono del medico
     * @param nombre Nombre y apellido del medico
     * @param numero representa el numero de licencia medico (unico por medico)
     * @param honorarioBasico Honorario basico del medico
     */
    public Medico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,int honorarioBasico) {
        super(dni, domicilio, ciudad, telefono, nombre, numero);
        this.Honorario = honorarioBasico;
    }

    @Override
    public double getHonorario() {
        return Honorario;
    }


}
