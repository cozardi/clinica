package usuarios;

import java.util.Hashtable;

import exceptions.DiasInvalidosException;
import exceptions.NoExisteException;
import lugares.*;

public abstract class Paciente  extends Usuarios implements PrioridadSala{
    protected String RangoEtario;
    protected Hashtable<Habitacion, Integer> internaciones;


    public Paciente(String dni, String domicilio, String ciudad, String telefono, String nombre, int numHistClinica, String RangoEtario) 
    {
        super(dni, domicilio, ciudad, telefono, nombre, numHistClinica);
        this.RangoEtario = RangoEtario;
    }
    
    /**
     * Registra una internaci�n en la estad�a del hospital actual del paciente. Si vuelve a una habitaci�n en la que ya estuvo, simplemente le suma d�as.
     * 
     * <b>pre:</b> La habitaci�n no es nula, dias > 0
     * <b>post:</b> La estadia queda registrada
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
    		else
    			throw new Exception("Error en los parametros del constructor Paciente. Falta Alguno");
    	}
    	else
    		throw new DiasInvalidosException("Cantidad de dias de internacion invalida: ", dias);
    }



}
