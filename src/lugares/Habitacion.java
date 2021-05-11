package lugares;

import exceptions.DiasInvalidosException;

public abstract class Habitacion {
    protected static double costoInicial = 1000;// preguntar que valor darle
    
    public Habitacion() {
    }

    /**
     * Pre: Los dias son > 0 <br>
     * Post: Retorna el valor de la internacion completo <br>
     * 
     * @param cantDias
     * @return double
     * @throws DiasInvalidosException si los dias <=0
     */
    public abstract double calculaArancel(int cantDias) throws DiasInvalidosException;
    
    public String IDTipoHabitacion()
    {
    	return "Habitacion";
    }

}
