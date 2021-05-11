package lugares;

import exceptions.DiasInvalidosException;

public class HabPrivada extends Habitacion {
    private static double costoAsignacion = 600;

    @Override
    public double calculaArancel(int cantDias) throws DiasInvalidosException {
        if (cantDias > 0) {
            if (cantDias == 1)
                return costoAsignacion + costoInicial;
            else if (cantDias <= 5)
                return cantDias * costoAsignacion * 1.3 + costoInicial;
            else
                return cantDias * costoAsignacion * 2 + costoInicial;
        } else
            throw new DiasInvalidosException("Dias menores a 0", cantDias);
    }

	@Override
	public String IDTipoHabitacion() {
		// TODO Auto-generated method stub
		return "Habitacion Privada";
	}
    
    

}
