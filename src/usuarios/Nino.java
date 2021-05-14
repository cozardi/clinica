package usuarios;

public class Nino extends Paciente implements PrioridadSala{


    /**
     * Metodo que crea un paciente nuevo.  <br>
     * <b>Pre: </b> Todos los parametros deben existir y ser validos. <br>
     * @param dni String, contiene el DNI del paciente. <br>
     * @param domicilio String, contiene el Domicilio en nombre y número. <br>
     * @param ciudad String, representa la ciudad de nacimiento. <br>
     * @param telefono String, contiene el numero de telefono del paciente. <br>
     * @param nombre Nombre y Apellido del paciente. <br>
     * <b>Post: </b>@return Crea un objeto de tipo Nino <br>
     */

    public Nino(String dni, String domicilio, String ciudad, String telefono, String nombre, String edad) {
        super(dni, domicilio, ciudad, telefono, nombre, edad);
    }

    @Override
    public boolean prioriza(PrioridadSala p) {
        return p.ganaNino();
    }

    @Override
    public boolean ganaJoven() {
        return false;
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
