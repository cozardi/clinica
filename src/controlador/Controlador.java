package controlador;

import modelo.ambulancia.Ambulancia;
import modelo.clinica.Clinica;
import modelo.exceptions.NoExisteAsociadoException;
import modelo.exceptions.YaExisteAsociadoException;
import modelo.usuarios.Asociado;
import vista.IVista;
import vista.VentanaInicio;
import vista.VistaSimulacion;

import javax.swing.*;
import java.awt.*;
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
	String accion = e.getActionCommand();
	Clinica clinica = Clinica.getInstance();
		if (accion.equalsIgnoreCase("AGREGAR")) {
			try {
				Asociado asociado = new Asociado(vista.getDni(),vista.getNombre()+' '+ vista.getApellido(),vista.getDomicilio(),vista.getTelefono(),vista.getCantLlamadas(),Ambulancia.get_instance());
				clinica.addAsociado(asociado);
				vista.actualizaLista(asociado);
			} catch (YaExisteAsociadoException ex) {
				JOptionPane.showMessageDialog((Component) this.vista,ex.getMessage());
			}
		}
		else if (accion.equalsIgnoreCase("ELIMINAR")){
			try {
				clinica.eliminaAsociado(vista.getAsociadoSelected());
			} catch (NoExisteAsociadoException noExisteAsociadoException) {
				JOptionPane.showMessageDialog((Component) this.vista,noExisteAsociadoException.getMessage());
			}
			this.vista.actualizaLista(vista.getAsociadoSelected());

		}
		else if (accion.equalsIgnoreCase("CONFIGURAR")){
			if (clinica.getOperario() == null)
				clinica.setOperario(vista.getCantLlamadas());
			else
				clinica.getOperario().setCantSolicitud(vista.getCantLlamadas());
		}
		else if (accion.equalsIgnoreCase("GUARDAR")){
			//SERIALIZACION
		}
		else if (accion.equalsIgnoreCase("CARGAR")){
			//CARGAR SERIAL
		}
		else if (accion.equalsIgnoreCase("COMENZAR")){
			this.vista=new VistaSimulacion();
			this.vista.addActionListener(this);
		}


	}
}
