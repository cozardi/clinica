package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

/**
 * Clase representantiva del state de la ambulancia
 */
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

    /**
     * Setea el atributo IState de la ambulancia con una instancia de DisponibleState
     */
    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new DisponibleState(ambulancia));
    }

    /**
     * No hace nada
     */
    @Override
    public void repararAmbulancia() {
    }

    /**
     * retorna la imagen correspondiente
     *
     * @return ImageIcon
     */
    @Override
    public ImageIcon getFoto() {
        return Recursos.REGRESANDO_CLINICA;
    }

    @Override
    public String toString() {
        return "regresando a la Clinica";
    }
}
