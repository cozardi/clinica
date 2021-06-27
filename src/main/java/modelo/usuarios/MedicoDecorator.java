package modelo.usuarios;

/**
 * Clase abstracta de los decoradores de medicos<br>
 * Contiene un atributo de tipo Imedico
 */

public abstract class MedicoDecorator extends Medico {
    protected Medico medico;

    public MedicoDecorator(Medico medico) {
        super(medico.dni, medico.domicilio, medico.ciudad, medico.telefono, medico.nombre, medico.numero,
                medico.honorario);
        this.medico = medico;
    }

}
