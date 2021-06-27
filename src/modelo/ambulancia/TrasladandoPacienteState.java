package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

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
        return Recursos.TRASLADANDO_PACIENTE;
    }

    @Override
    public String toString() {
        return "trasladando Paciente";
    }

}
