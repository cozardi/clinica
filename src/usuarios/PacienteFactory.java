package usuarios;

import exceptions.NoExisteException;

/**
 * Clase que usa el Patron Factory para crear los pacientes, teniendo en cuenta que estos pueden ser Nino, Joven o Mayor. <br>
 */

public class PacienteFactory{

    /**
     * Metodo que crea un paciente nuevo segun su rango etario <br>
     * <b>Pre: </b> Todos los parametros deben existir y ser validos. <br>
     * <b>Post: </b> En caso de haber sido posible, crea la clase Paciente, o lanza una excepcion si es que no pudo. <br>
     * @param dni
     * @param domicilio
     * @param ciudad
     * @param telefono
     * @param nombre
     * @param numHistClinica
     * @param rangoEtario Rango Etario del paciente, que es Nino, Joven o Mayor.<br>
     * @return Crea un objeto de tipo paciente <br>
     * @throws NoExisteException Si el rango etareo es erroneo lanza la excepcion y no crea el objeto <br>
     */

    public static Paciente PacienteFactory(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica,String rangoEtario) throws NoExisteException {
        Paciente paciente=null;
        if (rangoEtario.equalsIgnoreCase("Nino"))
            paciente=new Nino(dni,domicilio,ciudad,telefono,nombre,numHistClinica,rangoEtario);
        else if (rangoEtario.equalsIgnoreCase("Joven"))
            paciente=new Joven(dni,domicilio,ciudad,telefono,nombre,numHistClinica,rangoEtario);
        else if (rangoEtario.equalsIgnoreCase("Mayor"))
            paciente=new Mayor(dni,domicilio,ciudad,telefono,nombre,numHistClinica,rangoEtario);
        else
            throw new NoExisteException("Error en los parametros del constructor Paciente. Falta Alguno");
        return paciente;
    }
}
