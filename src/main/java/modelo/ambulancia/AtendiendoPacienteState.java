package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;

/**
 * Clase representantiva del state de la ambulancia
 */
public class AtendiendoPacienteState implements IState {
    private Ambulancia ambulancia;

    public AtendiendoPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleAtencionDomicilio(false);
        this.ambulancia.setDisponibleReparar(false);
        this.ambulancia.setDisponibleTraslado(false);
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
     * Setea el atributo IState de la ambulancia con una instancia de TrasladandoPacienteState
     */
    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));

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
        return Recursos.ATENDIENDO_PACIENTE;
    }

    @Override
    public String toString() {
        return "atendiendo Paciente";
    }
}
