package Usuarios;

public class Mayor extends Paciente implements PrioridadSala{

    public Mayor(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero) {
        super(dni, domicilio, ciudad, telefono, nombre, numero);
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
