package modelo.ambulancia;

import javax.swing.*;

public interface IState {
    void solicitaAtencionDomicilio();

    void solicitaTraslado();

    void vuelveClinica();

    void repararAmbulancia();

    ImageIcon getFoto();
}
