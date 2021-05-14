package usuarios;

public class Mayor extends Paciente{

    /**
     * Metodo que crea un paciente nuevo.  <br>
     * <b>Pre: </b> Todos los parametros deben existir y ser validos. <br>
     * @param dni String, contiene el DNI del paciente. <br>
     * @param domicilio String, contiene el Domicilio en nombre y número. <br>
     * @param ciudad String, representa la ciudad de nacimiento. <br>
     * @param telefono String, contiene el numero de telefono del paciente. <br>
     * @param nombre Nombre y Apellido del paciente. <br>
     * <b>Post: </b> Crea un objeto de tipo Mayor <br>
     */


    public Mayor(String dni, String domicilio, String ciudad, String telefono, String nombre, String rangoEtario) {
        super(dni, domicilio, ciudad, telefono, nombre, rangoEtario);
    }

    @Override
    public boolean prioriza(PrioridadSala p) {
        return p.ganaMayor();
    }

    @Override
    public boolean ganaJoven() {
        return true;
    }

    @Override
    public boolean ganaMayor() {
        return false;
    }

    @Override
    public boolean ganaNino() {
        return false;
    }


}
