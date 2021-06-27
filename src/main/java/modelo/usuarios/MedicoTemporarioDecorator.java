package modelo.usuarios;

/**
 * Crea un medico con contratacion Temporal
 */
public class MedicoTemporarioDecorator extends MedicoDecorator {

    public MedicoTemporarioDecorator(Medico medico) {
        super(medico);
        //setHonorario();
    }

    /**
     * Delega el seteo del honorario al medico que tiene como atributo, es decir
     * llama al setHonorario del medico que es quien calcula el honorario
     */
//    @Override
//    public void setHonorario() {
//        double honorario = medico.getHonorario();
//
//        this.honorario = honorario * 1.05;
//
//    }
    @Override
    public double getHonorario() {
        return super.getHonorario() * 1.05;
    }

}
