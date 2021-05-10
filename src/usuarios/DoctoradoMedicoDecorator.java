package usuarios;

/**
 * Crea un medico con Doctorado
 */
public class DoctoradoMedicoDecorator  extends MedicoDecorator{

    public DoctoradoMedicoDecorator(IMedico medico) {
        super(medico);
        setHonorario();
    }

    @Override
    public void setHonorario() {
        double honorario = medico.getHonorario();

        this.Honorario = honorario * 1.1;

    }
}
