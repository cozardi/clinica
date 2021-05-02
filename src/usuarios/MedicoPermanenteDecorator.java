package usuarios;

/**
 * Crea un medico con contratacion Permanente
 */
public class MedicoPermanenteDecorator extends MedicoDecorator{


    public MedicoPermanenteDecorator(IMedico medico) {
        super(medico);
    }

    @Override
    public double getHonorario() {
        double honorario = medico.getHonorario();

        return honorario * 1.1;

    }
}
