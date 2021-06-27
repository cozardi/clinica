package modelo.ambulancia;

import vista.Recursos;

import javax.swing.*;


/**
 * Clase representantiva del state de la ambulancia
 */
public class enTallerState implements IState {
    private Ambulancia ambulancia;

    public enTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setDisponibleTraslado(false);
        this.ambulancia.setDisponibleReparar(false);
        this.ambulancia.setDisponibleAtencionDomicilio(false);
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
     * Setea el atributo IState de la ambulancia con una instancia de RegresandoTallerState
     */
    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new RegresandoTallerState(ambulancia));
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
        return Recursos.ENTALLER;
    }

    @Override
    public String toString() {
        return "en el Taller";
    }
}
