package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

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
    }

    @Override
    public void solicitaTraslado() {
    }

    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new RegresandoTallerState(ambulancia));
    }

    @Override
    public void repararAmbulancia() {
    }

    @Override
    public ImageIcon getFoto() {
        return Recursos.ENTALLER;
    }

    @Override
    public String toString() {
        return "en el Taller";
    }
}
