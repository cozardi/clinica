package usuarios;

/**
 * Crea un medico con Doctorado
 */
public class DoctoradoMedicoDecorator  extends MedicoDecorator{

    public DoctoradoMedicoDecorator(IMedico medico) {
        super(medico);
    }

    @Override
    public double getHonorario() {
        double honorario = medico.getHonorario();

        return honorario * 1.1;

    }
}
