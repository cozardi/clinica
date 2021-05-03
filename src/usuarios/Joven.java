package usuarios;

public class Joven extends Paciente implements PrioridadSala{


    public Joven(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica, int edad) {
        super(dni, domicilio, ciudad, telefono, nombre, numHistClinica, edad);
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
