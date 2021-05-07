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
    protected Hashtable<Medico, Integer> consultas = new Hashtable<Medico, Integer>();
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
     * Registra una internaci�n en la estad�a del hospital actual del paciente. Si vuelve a una habitaci�n en la que ya estuvo, simplemente le suma d�as.<br>
     * 
     * <b>pre:</b> La habitaci�n no es nula, dias > 0 <br>
     * <b>post:</b> La estadia queda registrada <br>
     * 
     * @param hab: La habitaci�n en la que se intern� el paciente
     * @param dias: Los d�as que fue internado en dicha habitaci�n
     * 
     * @throws Exception si la habitaci�n es nula o los d�as no son v�lidos
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
    public void AgregaConsulta(Medico med) throws MedicoInvalidoException
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
