package usuarios;

/**
 * Crea un medico con contratacion Temporal
 */
public class MedicoTemporarioDecorator extends MedicoDecorator{


    public MedicoTemporarioDecorator(IMedico medico) {
        super(medico);
        setHonorario();
    }

    @Override
    public void setHonorario() {
        double honorario = medico.getHonorario();

        this.Honorario = honorario * 1.05;

    }

}
