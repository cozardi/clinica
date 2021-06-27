package vista;

import modelo.usuarios.Paciente;

import java.awt.event.ActionListener;

public interface IVistaFactura {

    void addActionListener(ActionListener actionListener);

    void Visible(boolean b);

    void actualizaListaPacientes();

    void muestraFactura(StringBuilder sb);
    
    void clearText();
    
    Paciente getPacienteSelected();
}
