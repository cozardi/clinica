package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import modelo.usuarios.Asociado;

/**
 * Clase observadora de Asociado, muestra los cambios de estado del mismo
 */
public class PanelAsociado extends JPanel implements Observer {
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private Asociado asociado;

    /**
     * Crea el panel y se agrega al asociado como observador
     */
    public PanelAsociado(Asociado asociado) {
        setPreferredSize(new Dimension(300, 300));
        setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), asociado.getNombre(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
        setLayout(new BorderLayout(0, 0));

        this.scrollPane = new JScrollPane();
        add(this.scrollPane, BorderLayout.CENTER);

        this.textArea = new JTextArea();
        this.scrollPane.setViewportView(this.textArea);

        this.asociado = asociado;
        this.asociado.addObserver(this);

    }

    /**
     * PRE: el Observable o debe ser la misma instancia del atributo asociado
     * Muestra en su textArea el cambio por escrito
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Asociado asociado = (Asociado) o;
        if (this.asociado == asociado) {
            this.textArea.append(asociado.getNombre() + ' ' + arg + "\n");
        } else
            throw new IllegalArgumentException();
    }

}