package lugares;

import exceptions.DiasInvalidosException;

public class HabTerapiaIntensiva extends Habitacion {
    private final double costoAsignacion = 1000;

    @Override
    public double calculaArancel(int cantDias) throws DiasInvalidosException {
        if (cantDias > 0) {
            return Math.pow(costoAsignacion, cantDias) + costoInicial;
        } else
            throw new DiasInvalidosException("Dias no mayores a 0", cantDias);
    }
}