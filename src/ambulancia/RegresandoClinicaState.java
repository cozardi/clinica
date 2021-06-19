package ambulancia;

public class RegresandoClinicaState implements IState {
    private Ambulancia ambulancia;

    public RegresandoClinicaState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {

    }

    @Override
    public void solicitaTraslado() {

    }

    @Override
    public void vuelveClinica() {

    }

    @Override
    public void repararAmbulancia() {

    }
}
