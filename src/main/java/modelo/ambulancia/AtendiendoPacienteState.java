package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

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

    }

    @Override
    public void solicitaTraslado() {

    }

    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));

    }

    @Override
    public void repararAmbulancia() {

    }

    @Override
    public ImageIcon getFoto() {
        return Recursos.ATENDIENDO_PACIENTE;
    }

    @Override
    public String toString() {
        return "atendiendo Paciente";
    }
}
