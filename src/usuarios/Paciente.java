package usuarios;

import java.util.ArrayList;
import java.util.Hashtable;

import exceptions.DiasInvalidosException;
import exceptions.MedicoInvalidoException;
import exceptions.NoExisteException;
import lugares.*;

/**
 * Establece los atributos de un paciente y almacena sus consultas
 */
public abstract class Paciente  extends Usuarios implements PrioridadSala{
    protected String RangoEtario;
    protected Hashtable<Habitacion, Integer> internaciones;
    protected Hashtable<IMedico, Integer> consultas = new Hashtable<IMedico, Integer>();
    private static int numHistoriaClinica=0;


	/**
	 * Metodo que crea un paciente nuevo. El numero de historia clinica es AutoIncremental. <br>
	 * <b>Pre: </b> Todos los parametros deben existir y ser validos. <br>
	 * @param dni String, contiene el DNI del paciente. <br>
	 * @param domicilio String, contiene el Domicilio en nombre y número. <br>
	 * @param ciudad String, representa la ciudad de nacimiento. <br>
	 * @param telefono String, contiene el numero de telefono del paciente. <br>
	 * @param nombre Nombre y Apellido del paciente. <br>
	 * @param rangoEtario Rango Etario del paciente, que es Nino, Joven o Mayor.<br>
	 */


	public Paciente(String dni, String domicilio, String ciudad, String telefono, String nombre, String rangoEtario)
    {
        super(dni, domicilio, ciudad, telefono, nombre,++numHistoriaClinica);
        this.RangoEtario = rangoEtario;
    }
    
    /**
     * Registra una internacion en la estadia del hospital actual del paciente. Si vuelve a una habitacion en la que ya estuvo, simplemente le suma dias.<br>
     * 
     * <b>pre:</b> La habitacion no es nula, dias > 0 <br>
     * <b>post:</b> La estadia queda registrada <br>
     * 
     * @param hab: La habitacion en la que se interno el paciente
     * @param dias: Los dias que fue internado en dicha habitacion
     * 
     * @throws Exception si la habitacion es nula o los dias no son validos
     */
    public void AgregaInternacion(Habitacion hab, int dias) throws Exception
    {
    	if (dias > 0)
    	{
    		if (hab != null)
    		{
            	if (internaciones.contains(hab))
            	{
            		internaciones.put(hab, internaciones.get(hab) + dias);
            	}
            	else
            	{
                	internaciones.put(hab, dias);
            	}
    		}
    		else //TODO excepcion de habitacion nula
    			throw new Exception("Error. Habitacion nula");
    	}
    	else
    		throw new DiasInvalidosException("Cantidad de dias de internacion invalida: ", dias);
    }
    
    
    public Hashtable<Habitacion, Integer> getInternaciones() {
		return internaciones;
	}

	public Hashtable<IMedico, Integer> getConsultas() {
		return consultas;
	}

	/**
     * Agrega una consulta medica a la estad�a actual del paciente <br>
     * 
     * @param med: El medico que hizo la consulta<br>
     * 
     * <b>pre:</b> El medico no es nulo<br>
     * <b>post:</b> La consulta queda registrada<br>
     * 
     * @throws MedicoInvalidoException si el medico no es valido
     */
    public void AgregaConsulta(IMedico med) throws MedicoInvalidoException
    {
    	if (med != null)
    	{
    		if (consultas.contains(med)) 
    		{
    			consultas.put(med, consultas.get(med) + 1);
    		}
    		else
    		{
    			consultas.put(med, 1);
    		}
    	}
    	
    	else
    	{
    		throw new MedicoInvalidoException("Error. Medico nulo");
    	}
    }

    /**
     * Borra las internaciones y consultas actuales del paciente
     * 
     * <b>pre:</b> Ninguna<br>
     * <b>post:</b> Las internaciones y consultas son reseteadas<br>
     * 
     */
    public void ReseteaPrestaciones()
    {
    	internaciones.clear();
    	consultas.clear();
    }


}
