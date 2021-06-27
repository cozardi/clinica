package vista;

import modelo.ambulancia.Ambulancia;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase observadora de Ambulancia, muestra los cambios de estado de la misma
 */
public class PanelAmbulancia extends JPanel implements Observer {
    ImageIcon foto = null;
    private JLabel labelFoto;
    Ambulancia ambulancia;

    /**
     * Instancia el panel y se añade como observador de la ambulancia
     *
     * @param ambulancia
     */
    public PanelAmbulancia(Ambulancia ambulancia) {
        setLayout(new GridLayout(1, 0, 0, 0));


        this.labelFoto = new JLabel(Recursos.DISPONIBLE);
        this.labelFoto.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.labelFoto);
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }

    /**
     * Pre: El Observable o debe ser la misma instancia que el atributo de la clase
     * Cambia las imagenes representativas del estado de la ambulancia
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (this.ambulancia == o) {
            ImageIcon fotoNueva = (ImageIcon) arg;
            this.labelFoto.setIcon(fotoNueva);
            this.labelFoto.paint(this.getGraphics());
        } else
            throw new IllegalArgumentException();
    }
}
