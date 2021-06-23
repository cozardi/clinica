package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import modelo.usuarios.Operario;

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
	private Operario asociado;
	
	/**
	 * Create the panel.
	 */
	public PanelOperario(Operario operario) {
		setPreferredSize(new Dimension(300, 300));
    	setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), operario.getNombre(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
    	setLayout(new BorderLayout(0, 0));
    	
    	this.scrollPane = new JScrollPane();
    	add(this.scrollPane, BorderLayout.CENTER);
    	
    	this.textArea = new JTextArea();
    	this.scrollPane.setViewportView(this.textArea);
    	
    	this.asociado = operario;
    	this.asociado.addObserver(this);
    	
	}

	@Override
	public void update(Observable o, Object arg) {
		Operario asociado = (Operario) o;
		if (this.asociado == asociado) {
			this.textArea.append(asociado.getNombre()+' '+ arg);
		}
		else
			throw new IllegalArgumentException();
	}

}
