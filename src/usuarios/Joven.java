package usuarios;

public class Joven extends Usuarios implements PrioridadSala{

    public Joven(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero) {
        super(dni, domicilio, ciudad, telefono, nombre, numero);
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


    @Override
    public int compareTo(Object o) {
        return 0;
    }

}