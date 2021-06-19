package ambulancia;

public interface IState {
    void solicitaAtencionDomicilio();
    void solicitaTraslado();
    void vuelveClinica();
    void repararAmbulancia();
}
