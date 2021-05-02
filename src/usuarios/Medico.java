package usuarios;

public class Medico extends Usuarios implements IMedico{
    private double Honorario;

    public Medico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,int honorarioBasico) {
        super(dni, domicilio, ciudad, telefono, nombre, numero);
        this.Honorario = honorarioBasico;
    }

    @Override
    public double getHonorario() {
        return Honorario;
    }


    @Override
    public int compareTo(Object o) {
        Medico m = (Medico) o;
        return this.numero - m.numero;
    }
}
