package vista;

import modelo.usuarios.Asociado;

import java.awt.event.ActionListener;
import java.util.Set;

/**
 * Interface para la pestaña de simulacion
 */
public interface IVistaSimulacion {
    void addActionListener(ActionListener acitonListener);

    void cargaPaneles(Set<Asociado> asociados);
}
