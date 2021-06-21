package controlador;

import modelo.clinica.Clinica;
import vista.IVista;
import vista.VentanaInicio;
import vista.VistaSimulacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
	private IVista vista = null;

	public Controlador() {
		this.vista = new VentanaInicio();
		this.vista.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {



	}
}
