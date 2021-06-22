package modelo.ambulancia;

public class AtendiendoPacienteState implements IState {
    private Ambulancia ambulancia;

    public AtendiendoPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleAtencionDomicilio(false);
        this.ambulancia.setDisponibleReparar(false);
        this.ambulancia.setDisponibleTraslado(false);
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
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));

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
