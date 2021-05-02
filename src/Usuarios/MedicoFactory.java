package Usuarios;

import Exceptions.NoExisteException;

public abstract class MedicoFactory {

    public static IMedico getMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,int honorarioBasico,String especialidad,String tipoContratacion) throws NoExisteException {
        IMedico medico = new Medico(dni,domicilio,ciudad,telefono,nombre,numero,honorarioBasico);
        IMedico respuesta;

        if (especialidad.toUpperCase().equals("CLINICO"))
            respuesta = new MedicoClinicoDecorator(medico);
        else if (especialidad.toUpperCase().equals("CIRUJANO"))
            respuesta = new MedicoCirujanoDecorator(medico);
        else if (especialidad.toUpperCase().equals("PEDIATRA"))
            respuesta = new MedicoPediatraDecorator(medico);
        else
            throw new NoExisteException("La especialidad ingresada no existe");

        if(tipoContratacion.toUpperCase().equals("PERMANENTE"))
            respuesta = new MedicoPermanenteDecorator(respuesta);
        else if(tipoContratacion.toUpperCase().equals("TEMPORAL"))
            respuesta = new MedicoPermanenteDecorator(respuesta);
        else
            throw new NoExisteException("El tipo de contratacion ingresado no existe");

        return respuesta;
    }

    public static IMedico getMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,int honorarioBasico,String especialidad,String tipoContratacion,String posgrado) throws NoExisteException {
        IMedico respuesta;

        respuesta = MedicoFactory.getMedico(dni,domicilio,ciudad,telefono,nombre,numero,honorarioBasico,especialidad,tipoContratacion);

        if (posgrado.toUpperCase().equals("MAGISTER"))
            respuesta = new MagisterMedicoDecorator(respuesta);
        else if (posgrado.toUpperCase().equals("DOCTORADO"))
            respuesta = new DoctoradoMedicoDecorator(respuesta);
        else
            throw new NoExisteException("El posgrado ingresado no existe");

        return respuesta;
    }

}
