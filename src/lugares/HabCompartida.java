package lugares;

import exceptions.DiasInvalidosException;

public class HabCompartida extends Habitacion {
    private static double costoAsignacion = 500;// Preguntar el costo este tmb

    public HabCompartida() {
        super();
    }

    @Override
    public double calculaArancel(int cantDias) throws DiasInvalidosException {
        if (cantDias > 0)
            return (costoAsignacion * cantDias) + costoInicial;
        else
            throw new DiasInvalidosException("Dias menores que cero", cantDias);
    }

	@Override
	public String IDTipoHabitacion() {
		// TODO Auto-generated method stub
		return "Habitacion Compartida";
	}

	public static double getCostoAsignacion() {
		return costoAsignacion;
	}
    
	
    

}
