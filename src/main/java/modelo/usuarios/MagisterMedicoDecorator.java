package modelo.usuarios;

/**
 * Crea un medico con Magister
 */
public class MagisterMedicoDecorator extends MedicoDecorator {

    public MagisterMedicoDecorator(Medico medico) {
        super(medico);
        //setHonorario();
    }

//    @Override
//    public void setHonorario() {
//        double honorario = medico.getHonorario();
//
//        this.honorario = honorario * 1.05;
//
//    }

    @Override
    public double getHonorario() {
        return this.medico.getHonorario() * 1.05;
    }
}
