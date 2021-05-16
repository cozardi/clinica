package usuarios;

/**
 * Medico con informacion base
 */
public abstract class MedicoBase extends IMedico {

    /**
     * Constructor de Medico base
     * 
     * @param dni             Contiene el DNI del medico
     * @param domicilio       contiene el domicilio del medico
     * @param ciudad          representa la ciudad de origen
     * @param telefono        contiene el telefono del medico
     * @param nombre          Nombre y apellido del medico
     * @param numero          representa el numero de licencia medico (unico por
     *                        medico)
     * @param honorarioBasico Honorario basico del medico
     */
    public MedicoBase(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,
            double honorarioBasico) {
        super(dni, domicilio, ciudad, telefono, nombre, numero, honorarioBasico);
    }

}
