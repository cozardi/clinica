package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

/**
 * Clase representantiva del state de la ambulancia
 */
public class DisponibleState implements IState {
    private Ambulancia ambulancia;

    public DisponibleState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleAtencionDomicilio(true);
        this.ambulancia.setDisponibleTraslado(true);
        this.ambulancia.setDisponibleReparar(true);
    }

    /**
     * Setea el atributo IState de la ambulancia con una instancia de AtendiendoPacienteState
     */
    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoPacienteState(ambulancia));
    }

    /**
     * Setea el atributo IState de la ambulancia con una instancia de RegresandoClinicaState
     */
    @Override
    public void solicitaTraslado() {
        ambulancia.setEstado(new RegresandoClinicaState(ambulancia));

    }

    /**
     * No hace nada
     */
    @Override
    public void vuelveClinica() {

    }

    /**
     * Setea el atributo IState de la ambulancia con una instancia de enTallerState
     */
    @Override
    public void repararAmbulancia() {
        ambulancia.setEstado(new enTallerState(ambulancia));
    }

    /**
     * retorna la imagen correspondiente
     *
     * @return ImageIcon
     */
    @Override
    public ImageIcon getFoto() {
        return Recursos.DISPONIBLE;
    }

    @Override
    public String toString() {
        return "disponible";
    }
}
