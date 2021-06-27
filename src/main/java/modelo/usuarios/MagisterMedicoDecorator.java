package modelo.usuarios;

/**
 * Crea un medico con Magister
 */
public class MagisterMedicoDecorator extends MedicoDecorator {

    public MagisterMedicoDecorator(Medico medico) {
        super(medico);
    }


    @Override
    public double getHonorario() {
        return this.medico.getHonorario() * 1.05;
    }
}
