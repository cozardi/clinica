package vista;

import java.awt.event.ActionListener;

import modelo.usuarios.Operario;


public interface IVista {
	void addActionListener(ActionListener actionListener);
	void actualizaLista(Operario asociado);
	void activaSimulacion();
	String getNombre();
	String getApellido();
	String getDni();
	String getDomicilio();
	String getTelefono();
	int getCantLlamadas();
	void Visible(boolean cond);
	Operario getAsociadoSelected();


	}
