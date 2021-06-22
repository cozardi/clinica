package modelo.ambulancia;

public class DisponibleState implements IState {
    private Ambulancia ambulancia;

    public DisponibleState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleAtencionDomicilio(true);
        this.ambulancia.setDisponibleTraslado(true);
        this.ambulancia.setDisponibleReparar(true);
    }

    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoPacienteState(ambulancia));
    }

    @Override
    public void solicitaTraslado() {
        ambulancia.setEstado(new RegresandoClinicaState(ambulancia));

    }

    @Override
    public void vuelveClinica() {

    }

    @Override
    public void repararAmbulancia() {
        ambulancia.setEstado(new RegresandoTallerState(ambulancia));
    }

    @Override
    public String toString() {
        return "disponible";
    }
}
