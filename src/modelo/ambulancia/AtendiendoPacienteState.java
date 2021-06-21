package modelo.ambulancia;

public class AtendiendoPacienteState implements IState {
    private Ambulancia ambulancia;

    public AtendiendoPacienteState(Ambulancia ambulancia) {
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
        ambulancia.setEstado(new DisponibleState(ambulancia));

    }

    @Override
    public void repararAmbulancia() {
        System.out.println("No es posible");

    }

    @Override
    public String toString() {
        return "atendiendo Paciente";
    }
}
