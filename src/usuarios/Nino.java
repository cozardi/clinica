package usuarios;

public class Nino extends Paciente implements PrioridadSala{


    public Nino(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica, String edad) {
        super(dni, domicilio, ciudad, telefono, nombre, numHistClinica, edad);
    }

    @Override
    public boolean prioriza(PrioridadSala p) {
        return p.beatsNino();
    }

    @Override
    public boolean beatsJoven() {
        return true;
    }

    @Override
    public boolean beatsMayor() {
        return false;
    }

    @Override
    public boolean beatsNino() {
        return false;
    }


}
