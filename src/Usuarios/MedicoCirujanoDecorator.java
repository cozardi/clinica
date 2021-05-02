package Usuarios;

public class MedicoCirujanoDecorator extends MedicoDecorator{

    public MedicoCirujanoDecorator(IMedico medico) {
        super(medico);
    }

    @Override
    public double getHonorario() {
        double honorario = medico.getHonorario();

        return honorario * 1.1;

    }
}
