package controlador;

import modelo.ambulancia.Ambulancia;
import modelo.clinica.Clinica;
import modelo.exceptions.FechaInvalidaException;
import modelo.exceptions.NoExisteAsociadoException;
import modelo.exceptions.PacienteInvalidoException;
import modelo.exceptions.YaExisteAsociadoException;
import modelo.usuarios.Asociado;
import persistencia.*;
import vista.IVista;
import vista.IVistaFactura;
import vista.IVistaSimulacion;
//import vista.VentanaFactura;
//import vista.VistaSimulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.GregorianCalendar;

public class Controlador implements ActionListener, WindowListener {
    private IVista vista = null;
    private IVistaSimulacion vistaSimulacion;
    private IVistaFactura vistaFactura;

    public Controlador(IVista ventanaInicio, IVistaFactura ventanaFactura, IVistaSimulacion ventanaSimulacion) {
        this.vista = ventanaInicio;
        this.vista.addActionListener(this);
        this.vistaFactura = ventanaFactura;
        this.vistaFactura.addActionListener(this);
        this.vistaSimulacion = ventanaSimulacion;
        this.vistaSimulacion.addActionListener(this);
        this.vista.addWindowListener(this);
        this.vista.actualizaLista(Clinica.getInstance().getAsociados());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        Clinica clinica = Clinica.getInstance();
        if (accion.equalsIgnoreCase("AGREGAR")) {
            try {
                Asociado asociado = new Asociado(vista.getDni(), vista.getNombre() + ' ' + vista.getApellido(), vista.getDomicilio(), vista.getTelefono(), vista.getCantLlamadas(), Ambulancia.get_instance());
                clinica.addAsociado(asociado);
                vista.actualizaLista(asociado);
            } catch (YaExisteAsociadoException ex) {
                JOptionPane.showMessageDialog((Component) this.vista, ex.getMessage());
            }
        } else if (accion.equalsIgnoreCase("ELIMINAR")) {
            try {
                clinica.eliminaAsociado(vista.getAsociadoSelected());
            } catch (NoExisteAsociadoException noExisteAsociadoException) {
                JOptionPane.showMessageDialog((Component) this.vista, noExisteAsociadoException.getMessage());
            }
            this.vista.actualizaLista(vista.getAsociadoSelected());

        } else if (accion.equalsIgnoreCase("Configurar")) {
            clinica.getOperario().setCantSolicitud(vista.getCantLlamadas());
            System.out.println(clinica.getOperario().getCantSolicitud());
        } else if (accion.equalsIgnoreCase("GUARDAR")) {
            //SERIALIZACION
        } else if (accion.equalsIgnoreCase("CARGAR")) {
            //CARGAR SERIAL
        } else if (accion.equalsIgnoreCase("COMENZAR")) {
            this.vista.activaSimulacion();
            this.vistaSimulacion.cargaPaneles(Clinica.getInstance().getAsociados());
            Clinica.getInstance().simulacion();
        } else if (accion.equalsIgnoreCase("GENERAR")) {
            try {
                StringBuilder sb = Clinica.getInstance().imprimeFacturaDePaciente(this.vistaFactura.getPacienteSelected(),new GregorianCalendar());
                this.vistaFactura.muestraFactura(sb);


            } catch (PacienteInvalidoException pacienteInvalidoException) {
                pacienteInvalidoException.printStackTrace();
            }
        }


    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        IPersistencia<Serializable> persistencia = new PersistenciaBIN();
        try {
            persistencia.abrirOutput("datos.bin");
            ClinicaDTO clinicaDto = UtilsDTO.ClinicaAClinicaDTO(Clinica.getInstance());
            persistencia.guardar(clinicaDto);
            persistencia.cerrarOutput();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            Clinica.getInstance().buscaMedico(1408).muestraReporte(new GregorianCalendar(2021, 5, 13),
                    new GregorianCalendar(2021, 5, 16));
        } catch (FechaInvalidaException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

    }


    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
