package usuarios;

public abstract class MedicoDecorator extends IMedico{
    protected IMedico medico;

    public MedicoDecorator(IMedico medico) {
        super(medico.dni,medico.domicilio,medico.ciudad,medico.telefono, medico.nombre, medico.numero, medico.Honorario);
        this.medico = medico;
    }

    @Override
    public void setHonorario() {
        medico.setHonorario();
    }

}
