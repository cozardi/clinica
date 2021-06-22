package vista;

import modelo.usuarios.Asociado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaSimulacion extends JFrame implements IVista{

    private JPanel mainPanel;
    private JButton btnTerminar;
    private JList estadoPersonas;
    private JProgressBar progressBar1;
    private JLabel labelTitulo;

    public VistaSimulacion() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        this.setVisible(true);
    }

    @Override
    public void addActionListener(ActionListener actionListener) {
        this.btnTerminar.addActionListener(actionListener);
    }

    @Override
    public void actualizaLista(Asociado asociado) {

    }

    @Override
    public String getNombre() {
        return null;
    }

    @Override
    public String getApellido() {
        return null;
    }

    @Override
    public String getDni() {
        return null;
    }

    @Override
    public String getDomicilio() {
        return null;
    }

    @Override
    public String getTelefono() {
        return null;
    }

    @Override
    public int getCantLlamadas() {
        return 0;
    }

    @Override
    public Asociado getAsociadoSelected() {
        return null;
    }
}
