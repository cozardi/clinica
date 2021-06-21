package ambulancia;

public class RegresandoClinicaState implements IState {
    private Ambulancia ambulancia;

    public RegresandoClinicaState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoPacienteState(ambulancia));
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
        return "regresando a la Clinica";
    }
}
