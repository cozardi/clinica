package usuarios;

/**
 * 
 * Clase abstracta de los decoradores de medicos<br>
 * Contiene un atributo de tipo Imedico
 * 
 * 
 */

public abstract class MedicoDecorator extends IMedico {
    protected IMedico medico;

    public MedicoDecorator(IMedico medico) {
        super(medico.dni, medico.domicilio, medico.ciudad, medico.telefono, medico.nombre, medico.numero,
                medico.Honorario);
        this.medico = medico;
    }

    /**
     * Delega el seteo del honorario al medico que tiene como atributo, es decir
     * llama al setHonorario del medico que es quien calcula el honorario
     */
    @Override
    public void setHonorario() {
        medico.setHonorario();
    }

}
