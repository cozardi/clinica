package modelo.ambulancia;

public class RegresandoTallerState implements IState {
    private Ambulancia ambulancia;

    public RegresandoTallerState(Ambulancia ambulancia) {
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
        ambulancia.setEstado(new DisponibleState(ambulancia));

    }

    @Override
    public void repararAmbulancia() {
        System.out.println("No es posible");

    }

    @Override
    public String toString() {
        return "regresando del Taller";
    }

}
