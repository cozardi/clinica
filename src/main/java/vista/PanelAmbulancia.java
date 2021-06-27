package vista;

import modelo.ambulancia.Ambulancia;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PanelAmbulancia extends JPanel implements Observer {
    ImageIcon foto = null;
    private JLabel labelFoto;
    Ambulancia ambulancia;

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

    @Override
    public void update(Observable o, Object arg) {
        if(this.ambulancia == o) {
            ImageIcon fotoNueva = (ImageIcon) arg;
           this.labelFoto.setIcon(fotoNueva);
           this.labelFoto.paint(this.getGraphics());
        }
        else
            throw new IllegalArgumentException();
    }
}
