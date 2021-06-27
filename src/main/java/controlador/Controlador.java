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

    /**
     * Constructor del controlador. <br>
     * <b>Pre:</b> Ningun parametro debe ser nulo. <br>
     * <b>Post: </b> El controlador tiene las referencias a las ventanas creadas.
     * @param ventanaInicio Es la ventana principal
     * @param ventanaFactura Referencia a la ventana que se encarga de generar la factura de un paciente.
     * @param ventanaSimulacion Referencia a la ventana encargada de realizar la simulacion del sistema de la ambulancia.
     */
    public Controlador(IVista ventanaInicio, IVistaFactura ventanaFactura, IVistaSimulacion ventanaSimulacion) {
        this.vista = ventanaInicio;
        this.vista.addActionListener(this);
        this.vistaFactura = ventanaFactura;
        this.vistaFactura.addActionListener(this);
        this.vistaSimulacion = ventanaSimulacion;
        this.vistaSimulacion.addActionListener(this);
        this.vista.addWindowListener(this);

    }

    /**
     * Metodo encargado de capturar los eventos que produce el usuario en la vista. <br>
     * Recibe el evento y delega al modelo las acciones necesarias para cumplir la peticion.
     * Luego de realizar esto, comunica a la vista los cambios que haya que hacer si es que son necesarios <br>
     * @param e Evento realizado por el usuario, que contiene un comando asociado para identificarlo.<br>
     *
     */
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
            this.vista.setConfigurarVisibilidad(false);
            this.vista.activaSimulacion();
            this.vistaSimulacion.cargaPaneles(Clinica.getInstance().getAsociados());
            Clinica.getInstance().simulacion();

        } else if (accion.equalsIgnoreCase("GENERAR")) {
            try {
                this.vistaFactura.clearText();
                StringBuilder sb = Clinica.getInstance().imprimeFacturaDePaciente(this.vistaFactura.getPacienteSelected(), new GregorianCalendar());
                this.vistaFactura.muestraFactura(sb);


            } catch (PacienteInvalidoException pacienteInvalidoException) {
                pacienteInvalidoException.printStackTrace();
            }
        }
        /*
        Esta seccion seria la encargado de delegar al modelo la creacion del informe medico si se tuviera la ventana de Medico.
        En la que mediante textfields ingresamos las fechas, y se selecciona el medico mediante una JList.
        if (accion.equalsIgonerCase("REPORTE"){
            try {
                Clinica.getInstance().buscaMedico(1408).muestraReporte(new GregorianCalendar(2021, 5, 13),
                        new GregorianCalendar(2021, 5, 16));
            } catch (FechaInvalidaException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }
        */

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    /**
     * Metodo que, al cerrar la ventana, realiza la persistencia de los datos <br>
     * <b>Post:</b> Se genera un archivo ".bin" con los datos serializados. <br>
     * @param e
     */
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
