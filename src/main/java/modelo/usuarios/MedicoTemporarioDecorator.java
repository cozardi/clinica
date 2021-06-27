package modelo.usuarios;

/**
 * Crea un medico con contratacion Temporal
 */
public class MedicoTemporarioDecorator extends MedicoDecorator {

    public MedicoTemporarioDecorator(Medico medico) {
        super(medico);
    }

    /**
     * Retorna el honorario del medico
     *
     * @return double
     */
    @Override
    public double getHonorario() {
        return super.getHonorario() * 1.05;
    }

}
