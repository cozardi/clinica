package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

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

    }

    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new DisponibleState(ambulancia));

    }

    @Override
    public void repararAmbulancia() {
    }

    @Override
    public ImageIcon getFoto() {
        return Recursos.REGRESANDO_TALLER;
    }

    @Override
    public String toString() {
        return "regresando del Taller";
    }

}
