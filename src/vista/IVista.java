package vista;

import java.awt.event.ActionListener;

import modelo.usuarios.Asociado;


public interface IVista {
	void addActionListener(ActionListener actionListener);
	void actualizaLista(Asociado asociado);
	String getNombre();
	String getApellido();
	String getDni();
	String getDomicilio();
	String getTelefono();
	int getCantLlamadas();
	void Visible(boolean cond);
	Asociado getAsociadoSelected();


	}
