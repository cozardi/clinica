package usuarios;

/**
 * Crea un medico con contratacion Permanente
 */
public class MedicoPermanenteDecorator extends MedicoDecorator{


    public MedicoPermanenteDecorator(IMedico medico) {
        super(medico);
        setHonorario();
    }

    @Override
    public void setHonorario() {
        double honorario = medico.getHonorario();

        this.Honorario = honorario * 1.1;

    }
}
