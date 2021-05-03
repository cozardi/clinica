package usuarios;

public class Mayor extends Paciente{

    public Mayor(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica, String rangoEtario) {
        super(dni, domicilio, ciudad, telefono, nombre, numHistClinica,rangoEtario);
    }

    @Override
    public boolean prioriza(PrioridadSala p) {
        return p.beatsMayor();
    }

    @Override
    public boolean beatsJoven() {
        return false;
    }

    @Override
    public boolean beatsMayor() {
        return false;
    }

    @Override
    public boolean beatsNino() {
        return true;
    }


}
