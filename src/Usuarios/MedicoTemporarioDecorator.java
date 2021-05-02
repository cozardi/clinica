package Usuarios;

public class MedicoTemporarioDecorator extends MedicoDecorator{


    public MedicoTemporarioDecorator(IMedico medico) {
        super(medico);
    }

    @Override
    public double getHonorario() {
        double honorario = medico.getHonorario();

        return honorario * 1.05;

    }

}
