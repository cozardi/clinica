package usuarios;

import java.util.ArrayList;
import java.util.Hashtable;

import exceptions.DiasInvalidosException;
import exceptions.MedicoInvalidoException;
import exceptions.NoExisteException;
import lugares.*;

public abstract class Paciente  extends Usuarios implements PrioridadSala{
    protected String RangoEtario;
    protected Hashtable<Habitacion, Integer> internaciones = new Hashtable<Habitacion, Integer>();
    protected Hashtable<Medico, Integer> consultas = new Hashtable<Medico, Integer>();



    public Paciente(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica, String RangoEtario) 
    {
        super(dni, domicilio, ciudad, telefono, nombre, numHistClinica);
        this.RangoEtario = RangoEtario;
    }
    
    /**
     * Registra una internación en la estadía del hospital actual del paciente. Si vuelve a una habitación en la que ya estuvo, simplemente le suma días.
     * 
     * <b>pre:</b> La habitación no es nula, dias > 0
     * <b>post:</b> La estadia queda registrada
     * 
     * @param hab: La habitación en la que se internó el paciente
     * @param dias: Los días que fue internado en dicha habitación
     * 
     * @throws Exception si la habitación es nula o los días no son válidos
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
     * Agrega una consulta medica a la estadía actual del paciente
     * 
     * @param med: El medico que hizo la consulta
     * 
     * <b>pre:</b> El medico no es nulo
     * <b>post:</b> La consulta queda registrada
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
     * <b>pre:</b> Ninguna
     * <b>post:</b> Las internaciones y consultas son reseteadas
     * 
     */
    public void ReseteaPrestaciones()
    {
    	internaciones.clear();
    	consultas.clear();
    }


}
