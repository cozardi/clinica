package vista;

import modelo.usuarios.Paciente;

import java.awt.event.ActionListener;

/**
 * Interface para la pestaña de la factrua
 */
public interface IVistaFactura {

    void addActionListener(ActionListener actionListener);

    void Visible(boolean b);

    void actualizaListaPacientes();

    void muestraFactura(StringBuilder sb);

    void clearText();

    Paciente getPacienteSelected();
}
