package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

/**
 * Clase representantiva del state de la ambulancia
 */
public class TrasladandoPacienteState implements IState {
    private Ambulancia ambulancia;

    public TrasladandoPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleAtencionDomicilio(true);
        this.ambulancia.setDisponibleTraslado(false);
        this.ambulancia.setDisponibleReparar(false);
    }

    /**
     * No hace nada
     */
    @Override
    public void solicitaAtencionDomicilio() {
    }

    /**
     * No hace nada
     */
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
        return Recursos.TRASLADANDO_PACIENTE;
    }

    @Override
    public String toString() {
        return "trasladando Paciente";
    }

}
