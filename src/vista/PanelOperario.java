package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import modelo.clinica.Operario;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelOperario extends JPanel implements Observer {
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private Operario operario;

    /**
     * Create the panel.
     */
    public PanelOperario(Operario operario) {
        setPreferredSize(new Dimension(300, 300));
        setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Operario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
        setLayout(new BorderLayout(0, 0));

        this.scrollPane = new JScrollPane();
        add(this.scrollPane, BorderLayout.CENTER);

        this.textArea = new JTextArea();
        this.scrollPane.setViewportView(this.textArea);

        this.operario = operario;
        this.operario.addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        Operario operario = (Operario) o;
        if (this.operario == operario) {
            this.textArea.append("El operario " + arg + "\n");
        } else
            throw new IllegalArgumentException();
    }

}