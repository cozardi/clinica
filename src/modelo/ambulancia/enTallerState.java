package modelo.ambulancia;

public class enTallerState implements IState {
    private Ambulancia ambulancia;

    public enTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleTraslado(false);
        this.ambulancia.setDisponibleReparar(false);
        this.ambulancia.setDisponibleAtencionDomicilio(false);
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

    @Override
    public String toString() {
        return "en el Taller";
    }
}
