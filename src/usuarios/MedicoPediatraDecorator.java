package usuarios;

/**
 * Crea un medico Pediatra
 */
public class MedicoPediatraDecorator extends MedicoDecorator{

    public MedicoPediatraDecorator(IMedico medico) {
        super(medico);
    }

    @Override
    public double getHonorario() {
        double honorario = medico.getHonorario();

        return honorario*1.07;

    }
}
