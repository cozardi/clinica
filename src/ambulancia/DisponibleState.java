package ambulancia;

public class DisponibleState implements IState {
    private Ambulancia ambulancia;

    public DisponibleState(Ambulancia ambulancia) {
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
