package usuarios;

public abstract class MedicoDecorator implements IMedico{
    protected IMedico medico;

    public MedicoDecorator(IMedico medico) {
        this.medico = medico;
    }

    @Override
    public double getHonorario() {
        return medico.getHonorario();
    }

}
