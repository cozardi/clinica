package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

public class RegresandoClinicaState implements IState {
    private Ambulancia ambulancia;

    public RegresandoClinicaState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleAtencionDomicilio(true);
        this.ambulancia.setDisponibleReparar(false);
        this.ambulancia.setDisponibleTraslado(false);
    }

    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoPacienteState(ambulancia));
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
        return Recursos.REGRESANDO_CLINICA;
    }

    @Override
    public String toString() {
        return "regresando a la Clinica";
    }
}
