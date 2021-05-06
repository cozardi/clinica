package usuarios;

public class Joven extends Paciente{

    /**
     * Metodo que crea un paciente nuevo.  <br>
     * <b>Pre: </b> Todos los parametros deben existir y ser validos. <br>
     * @param dni String, contiene el DNI del paciente. <br>
     * @param domicilio String, contiene el Domicilio en nombre y número. <br>
     * @param ciudad String, representa la ciudad de nacimiento. <br>
     * @param telefono String, contiene el numero de telefono del paciente. <br>
     * @param nombre Nombre y Apellido del paciente. <br>
     * @return Crea un objeto de tipo Joven <br>
     */


    public Joven(String dni, String domicilio, String ciudad, String telefono, String nombre, String edad) {
        super(dni, domicilio, ciudad, telefono, nombre, edad);
    }

    @Override
    public boolean prioriza(PrioridadSala p) {
        return p.beatsJoven();
    }

    @Override
    public boolean beatsJoven() {
        return false;
    }

    @Override
    public boolean beatsMayor() {
        return true;
    }

    @Override
    public boolean beatsNino() {
        return false;
    }



}
