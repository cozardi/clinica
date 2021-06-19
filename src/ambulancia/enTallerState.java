package ambulancia;

public class enTallerState implements IState {
    private Ambulancia ambulancia;

    public enTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
        System.out.println("No es posible");

    }

    @Override
    public void solicitaTraslado() {
        System.out.println("No es posible");

    }

    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new RegresandoTallerState(ambulancia));
    }

    @Override
    public void repararAmbulancia() {

    }
}
