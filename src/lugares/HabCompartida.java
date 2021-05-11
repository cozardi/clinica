package lugares;

import exceptions.DiasInvalidosException;

public class HabCompartida extends Habitacion {

    public HabCompartida() {
        super(500);
    }

    @Override
    public double calculaArancel(int cantDias) throws DiasInvalidosException {
        if (cantDias > 0)
            return (costoAsignacion * cantDias) + costoInicial;
        else
            throw new DiasInvalidosException("Dias menores que cero", cantDias);
    }

}
