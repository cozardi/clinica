package modelo.usuarios;

/**
 * Crea un medico con Doctorado
 */
public class DoctoradoMedicoDecorator extends MedicoDecorator {

    public DoctoradoMedicoDecorator(Medico medico) {
        super(medico);
        //setHonorario();
    }

    /**
     * Incrementa el honorario del medico en un diez porciento
     */
//    @Override
//    public void setHonorario() {
//        double honorario = medico.getHonorario();
//        this.honorario = honorario * 1.1;
//
//    }

    /**
     * @return el honorario del medico basico inicial, y lo incrementa lo que corresponde
     */
    @Override
    public double getHonorario() {

        return this.medico.getHonorario() * 1.1;
    }
}
