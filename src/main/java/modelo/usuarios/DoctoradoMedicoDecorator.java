package modelo.usuarios;

/**
 * Crea un medico con Doctorado
 */
public class DoctoradoMedicoDecorator extends MedicoDecorator {

    public DoctoradoMedicoDecorator(Medico medico) {
        super(medico);
    }


    /**
     * @return el honorario del medico basico inicial, y lo incrementa lo que corresponde
     */
    @Override
    public double getHonorario() {

        return this.medico.getHonorario() * 1.1;
    }
}
