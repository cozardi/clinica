package usuarios;

import java.util.ArrayList;
import java.util.Hashtable;

import exceptions.DiasInvalidosException;
import exceptions.NoExisteException;
import lugares.*;

public abstract class Paciente  extends Usuarios implements PrioridadSala{
    protected String RangoEtario;
    protected Hashtable<Habitacion, Integer> internaciones;
    protected ArrayList<Medico> consultas;


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
    		else
    			throw new Exception("Error en los parametros del constructor Paciente. Falta Alguno");
    	}
    	else
    		throw new DiasInvalidosException("Cantidad de dias de internacion invalida: ", dias);
    }
    
    
    /**
     * 
     * @param med: El medico que hizo la consulta
     * 
     * <b>pre:</b> El medico no es nulo
     * <b>post:</b> La consulta queda registrada
     * 
     * @throws Exception si el medico no es valido
     */
    public void AgregaConsulta(Medico med) throws Exception
    {
    	if (med != null)
    	{
    		consultas.add(med);
    	}
    	else
    	{
    		throw new Exception("Error. Medico no válido");
    	}
    }



}
