package vista;

import modelo.usuarios.Asociado;

import java.awt.event.ActionListener;
import java.util.Set;

public interface IVistaSimulacion {
    void addActionListener(ActionListener acitonListener);

    void cargaPaneles(Set<Asociado> asociados);
}
