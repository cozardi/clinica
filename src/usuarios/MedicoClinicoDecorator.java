package usuarios;

public class MedicoClinicoDecorator extends MedicoDecorator{

    public MedicoClinicoDecorator(IMedico medico) {
        super(medico);
    }

    @Override
    public double getHonorario() {
        double honorario = medico.getHonorario();

        return honorario*1.05;

    }

}
