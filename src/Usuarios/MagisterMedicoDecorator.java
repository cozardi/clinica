package Usuarios;

public class MagisterMedicoDecorator extends MedicoDecorator{


    public MagisterMedicoDecorator(IMedico medico) {
        super(medico);
    }

    @Override
    public double getHonorario() {
        double honorario = medico.getHonorario();

        return honorario*1.05;

    }
}
