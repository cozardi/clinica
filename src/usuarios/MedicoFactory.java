package usuarios;

import exceptions.NoExisteException;

public abstract class MedicoFactory {

    /**
     * Crea el medico segun su especialidad, tipo de Contratacion y posgrado (en caso de tenerlo).
     * @param dni
     * @param domicilio
     * @param ciudad
     * @param telefono
     * @param nombre
     * @param numero
     * @param honorarioBasico
     * @param especialidad
     * @param tipoContratacion
     * @return Un medico especifico
     * @throws NoExisteException Una advertencia sobre algun parametro mal ingresado
     */
    public static IMedico getMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,int honorarioBasico,String especialidad,String tipoContratacion) throws NoExisteException {
        IMedico medico = new Medico(dni,domicilio,ciudad,telefono,nombre,numero,honorarioBasico);
        IMedico respuesta;

        if (especialidad.equalsIgnoreCase("CLINICO"))
            respuesta = new MedicoClinicoDecorator(medico);
        else if (especialidad.equalsIgnoreCase("CIRUJANO"))
            respuesta = new MedicoCirujanoDecorator(medico);
        else if (especialidad.equalsIgnoreCase("PEDIATRA"))
            respuesta = new MedicoPediatraDecorator(medico);
        else
            throw new NoExisteException("La especialidad ingresada no existe");

        if(tipoContratacion.equalsIgnoreCase("PERMANENTE"))
            respuesta = new MedicoPermanenteDecorator(respuesta);
        else if(tipoContratacion.equalsIgnoreCase("TEMPORAL"))
            respuesta = new MedicoPermanenteDecorator(respuesta);
        else
            throw new NoExisteException("El tipo de contratacion ingresado no existe");

        return respuesta;
    }

    public static IMedico getMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,int honorarioBasico,String especialidad,String tipoContratacion,String posgrado) throws NoExisteException {
        IMedico respuesta;

        respuesta = MedicoFactory.getMedico(dni,domicilio,ciudad,telefono,nombre,numero,honorarioBasico,especialidad,tipoContratacion);

        if (posgrado.equalsIgnoreCase("MAGISTER"))
            respuesta = new MagisterMedicoDecorator(respuesta);
        else if (posgrado.equalsIgnoreCase("DOCTORADO"))
            respuesta = new DoctoradoMedicoDecorator(respuesta);
        else
            throw new NoExisteException("El posgrado ingresado no existe");

        return respuesta;
    }

}
