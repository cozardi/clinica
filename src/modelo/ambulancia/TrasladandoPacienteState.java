package modelo.ambulancia;

public class TrasladandoPacienteState implements IState {
    private Ambulancia ambulancia;

    public TrasladandoPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleAtencionDomicilio(true);
        this.ambulancia.setDisponibleTraslado(false);
        this.ambulancia.setDisponibleReparar(false);
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
        return "trasladando Paciente";
    }

}
