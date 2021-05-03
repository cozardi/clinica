package usuarios;

public class Mayor extends Usuarios implements PrioridadSala{

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

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
