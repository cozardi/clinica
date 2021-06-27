package vista;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Set;

import modelo.usuarios.Asociado;

/**
 * Interface para la pestaña de inicio de la aplicacion
 */
public interface IVista {
    void addActionListener(ActionListener actionListener);

    void actualizaLista(Set<Asociado> asociadoSet);

    void actualizaLista(Asociado asociado);

    void activaSimulacion();

    String getNombre();

    String getApellido();

    String getDni();

    String getDomicilio();

    String getTelefono();

    int getCantLlamadas();

    void Visible(boolean cond);

    void setConfigurarVisibilidad(boolean cond);

    Asociado getAsociadoSelected();

    void addWindowListener(WindowListener windowListener);


}
