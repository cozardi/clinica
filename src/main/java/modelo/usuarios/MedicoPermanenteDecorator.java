package modelo.usuarios;

/**
 * Crea un medico con contratacion Permanente
 */
public class MedicoPermanenteDecorator extends MedicoDecorator {

    public MedicoPermanenteDecorator(Medico medico) {
        super(medico);
    }

    /**
     * Retorna el honorario del medico
     *
     * @return double
     */
    @Override
    public double getHonorario() {
        return super.getHonorario() * 1.1;
    }
}
