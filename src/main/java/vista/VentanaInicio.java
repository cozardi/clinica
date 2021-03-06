package vista;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListSelectionModel;


import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import modelo.ambulancia.Ambulancia;
import modelo.clinica.Clinica;
import modelo.usuarios.Asociado;
import modelo.usuarios.Paciente;
import persistencia.ClinicaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilsDTO;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana principal de la interfaz grafica. Contiene pestañas de acceso a las demas ventanas.
 */

public class VentanaInicio extends JFrame implements IVista, IVistaSimulacion, IVistaFactura, KeyListener, ListSelectionListener, ActionListener {
    private static boolean tieneActionListener = false;
    private JPanel contentPane;
    private JPanel panelContenedorAsociados;
    private JPanel panelCentral;
    private JPanel panelAgregar;
    private JButton btnAgregar;
    private JPanel panelEliminar;
    private JButton btnEliminar;
    private JPanel panelOperario;
    private JButton btnOperario;
    private JPanel panelComenzar;
    private JButton btnComenzar;
    private JPanel panelEste;
    private JPanel panelNombre;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JPanel panelApellido;
    private JLabel lblApellido;
    private JTextField textFieldApellido;
    private JPanel panelDni;
    private JLabel lblDni;
    private JTextField textFieldDni;
    private JPanel panelDomicilio;
    private JLabel lblDomicilio;
    private JTextField textFieldDomicilio;
    private JPanel panelTelefono;
    private JLabel lblTelefono;
    private JTextField textFieldTelefono;
    private JPanel panelLlamados;
    private JLabel lblLlamados;
    private JTextField textFieldLlamadas;
    private JPanel panelOeste;
    private JLabel lblListaAsociados;
    private JScrollPane scrollPane;
    private JList<Asociado> listAsociados;
    private DefaultListModel<Asociado> modeloLista = new DefaultListModel<Asociado>();
    private JTabbedPane tabbedPane;
    private JPanel panelContenedorSimulacion;
    private JPanel panelIzquierda;
    private JScrollPane scrollPaneAsociados;
    private JPanel panelCentralAsociados;
    private JPanel panelEstadoOperario;
    private JPanel panelEstadoAmbulancia;
    private JPanel panelFacturas;
    private JPanel panel_FacturaLista;
    private JList<Paciente> listPacientes;
    private DefaultListModel<Paciente> modeloPaciente = new DefaultListModel<Paciente>();
    private JScrollPane scrollPacientes;
    private JLabel lblNewLabel;
    private JPanel panel_FacturaCentro;
    private JPanel panelBotonFactura;
    private JButton btnGenerar;
    private JTextArea textoFactura;
    private JScrollPane scrollPaneFactura;

    /**
     * Constructor de la ventana.
     */
    public VentanaInicio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        panelContenedorAsociados = new JPanel();
        panelContenedorAsociados.setLayout(new GridLayout(0, 3, 0, 0));

        panelOeste = new JPanel();
        panelContenedorAsociados.add(panelOeste);
        panelOeste.setLayout(new BorderLayout(0, 0));

        lblListaAsociados = new JLabel("Lista de Asociados");
        lblListaAsociados.setHorizontalAlignment(SwingConstants.CENTER);
        panelOeste.add(lblListaAsociados, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        panelOeste.add(scrollPane, BorderLayout.CENTER);

        listAsociados = new JList<Asociado>();
        listAsociados.setModel(modeloLista);
        listAsociados.addListSelectionListener(this);
        listAsociados.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(listAsociados);

        panelCentral = new JPanel();
        panelContenedorAsociados.add(panelCentral);
        panelCentral.setLayout(new GridLayout(4, 1, 0, 0));

        panelAgregar = new JPanel();
        panelCentral.add(panelAgregar);
        panelAgregar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnAgregar = new JButton("Agregar Asociado");
        btnAgregar.setActionCommand("Agregar");
        btnAgregar.setEnabled(false);
        panelAgregar.add(btnAgregar);

        panelEliminar = new JPanel();
        panelCentral.add(panelEliminar);

        btnEliminar = new JButton("Eliminar Asociado");
        btnEliminar.setActionCommand("Eliminar");
        btnEliminar.setEnabled(false);
        panelEliminar.add(btnEliminar);

        panelOperario = new JPanel();
        panelCentral.add(panelOperario);

        btnOperario = new JButton("Configurar operario");
        btnOperario.setActionCommand("Configurar");
        btnOperario.setEnabled(false);
        panelOperario.add(btnOperario);

        panelComenzar = new JPanel();
        panelCentral.add(panelComenzar);

        btnComenzar = new JButton("Comenzar Simulacion");
        btnComenzar.setActionCommand("Comenzar");
        btnComenzar.setEnabled(false);
        panelComenzar.add(btnComenzar);

        panelEste = new JPanel();
        panelContenedorAsociados.add(panelEste);
        panelEste.setLayout(new GridLayout(6, 1, 0, 0));

        panelNombre = new JPanel();
        panelEste.add(panelNombre);

        lblNombre = new JLabel("Nombre:");
        panelNombre.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        this.textFieldNombre.addKeyListener(this);
        panelNombre.add(textFieldNombre);

        panelApellido = new JPanel();
        panelEste.add(panelApellido);

        lblApellido = new JLabel("Apellido:");
        panelApellido.add(lblApellido);

        textFieldApellido = new JTextField();
        textFieldApellido.setColumns(10);
        this.textFieldApellido.addKeyListener(this);
        panelApellido.add(textFieldApellido);

        panelDni = new JPanel();
        panelEste.add(panelDni);
        panelDni.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblDni = new JLabel("Dni:");
        lblDni.setHorizontalAlignment(SwingConstants.CENTER);
        panelDni.add(lblDni);

        textFieldDni = new JTextField();
        textFieldDni.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldDni.setColumns(10);
        this.textFieldDni.addKeyListener(this);
        panelDni.add(textFieldDni);

        panelDomicilio = new JPanel();
        panelEste.add(panelDomicilio);

        lblDomicilio = new JLabel("Domicilio:");
        panelDomicilio.add(lblDomicilio);

        textFieldDomicilio = new JTextField();
        textFieldDomicilio.setColumns(10);
        this.textFieldDomicilio.addKeyListener(this);
        panelDomicilio.add(textFieldDomicilio);

        panelTelefono = new JPanel();
        panelEste.add(panelTelefono);

        lblTelefono = new JLabel("Telefono:");
        panelTelefono.add(lblTelefono);

        textFieldTelefono = new JTextField();
        textFieldTelefono.setColumns(10);
        this.textFieldTelefono.addKeyListener(this);
        panelTelefono.add(textFieldTelefono);


        panelLlamados = new JPanel();
        panelEste.add(panelLlamados);

        lblLlamados = new JLabel("Cant llamados");
        panelLlamados.add(lblLlamados);

        textFieldLlamadas = new JTextField();
        textFieldLlamadas.setColumns(10);
        this.textFieldLlamadas.addKeyListener(this);
        contentPane.setLayout(new BorderLayout(0, 0));

        panelLlamados.add(textFieldLlamadas);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);
        tabbedPane.addTab("Asociados", null, panelContenedorAsociados, null);

        panelContenedorSimulacion = new JPanel();
        tabbedPane.addTab("Simulacion", null, panelContenedorSimulacion, null);
        panelContenedorSimulacion.setLayout(new BorderLayout(0, 0));

        panelIzquierda = new JPanel();
        panelContenedorSimulacion.add(panelIzquierda, BorderLayout.WEST);
        panelIzquierda.setLayout(new GridLayout(2, 1, 0, 5));

        panelEstadoOperario = new JPanel();
        FlowLayout fl_panelEstadoOperario = (FlowLayout) panelEstadoOperario.getLayout();
        fl_panelEstadoOperario.setHgap(80);
        panelIzquierda.add(panelEstadoOperario);

        panelEstadoAmbulancia = new JPanel();
        panelIzquierda.add(panelEstadoAmbulancia);

        scrollPaneAsociados = new JScrollPane();
        panelContenedorSimulacion.add(scrollPaneAsociados, BorderLayout.CENTER);

        panelCentralAsociados = new JPanel();
        scrollPaneAsociados.setViewportView(panelCentralAsociados);
        panelCentralAsociados.setLayout(new GridLayout(0, 5, 6, 6));
        tabbedPane.setEnabledAt(1, false);

        this.panelFacturas = new JPanel();
        this.tabbedPane.addTab("Facturas", null, this.panelFacturas, null);
        this.panelFacturas.setLayout(new BorderLayout(0, 0));

        this.panel_FacturaLista = new JPanel();
        this.panelFacturas.add(this.panel_FacturaLista, BorderLayout.NORTH);
        this.panel_FacturaLista.setLayout(new BorderLayout(0, 0));

        this.scrollPacientes = new JScrollPane();
        this.panel_FacturaLista.add(this.scrollPacientes);

        this.listPacientes = new JList<Paciente>();
        this.listPacientes.setVisibleRowCount(7);
        this.listPacientes.setModel(modeloPaciente);
        this.listPacientes.addListSelectionListener(this);
        this.listPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.scrollPacientes.setViewportView(this.listPacientes);


        this.lblNewLabel = new JLabel("Lista de Pacientes");
        this.scrollPacientes.setColumnHeaderView(this.lblNewLabel);

        this.panel_FacturaCentro = new JPanel();
        this.panelFacturas.add(this.panel_FacturaCentro, BorderLayout.CENTER);
        panel_FacturaCentro.setLayout(new BorderLayout(0, 0));

        panelBotonFactura = new JPanel();
        panel_FacturaCentro.add(panelBotonFactura, BorderLayout.NORTH);

        btnGenerar = new JButton("Generar Factura");
        btnGenerar.setEnabled(false);
        btnGenerar.setActionCommand("Generar");
        panelBotonFactura.add(btnGenerar);

        textoFactura = new JTextArea();
        textoFactura.setEditable(false);
        scrollPaneFactura = new JScrollPane(textoFactura);
        textoFactura.setColumns(3);
        panel_FacturaCentro.add(scrollPaneFactura, BorderLayout.SOUTH);
        panel_FacturaCentro.add(scrollPaneFactura, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * Asigna a los botones pertinentes el actionlistener, el cual es el constructor.
     * @param actionListener
     */
    @Override
    public void addActionListener(ActionListener actionListener) {
        if (!tieneActionListener) {
            this.btnAgregar.addActionListener(actionListener);
            this.btnComenzar.addActionListener(actionListener);
            this.btnOperario.addActionListener(actionListener);
            this.btnEliminar.addActionListener(actionListener);
            this.btnGenerar.addActionListener(actionListener);
            tieneActionListener = true;
        }
    }

    /**
     * Carga los paneles de los asociados en la ventana de la simulacion.
     *
     * @param asociados
     */
    @Override
    public void cargaPaneles(Set<Asociado> asociados) {
        PanelAmbulancia panelAM = new PanelAmbulancia(Ambulancia.get_instance());
        this.panelEstadoAmbulancia.add(panelAM);

        for (Asociado asociado : asociados) {
            if (asociado.getCantSolicitudes() > 0) {
                PanelAsociado panelAux = new PanelAsociado(asociado);
                this.panelCentralAsociados.add(panelAux);
            }
        }
        PanelOperario panelOP = new PanelOperario(Clinica.getInstance().getOperario());
        this.panelEstadoOperario.add(panelOP);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Metodo que se encarga de habilitar los botones de configurar operario o agregar asociado si se cumplen las condiciones necesarias.
     * Chequea que no se ingresen String nulos, y que las solicitudes sean mayores a 0.
     * <b>Post:</b> Habilita los botones necesarios.
     * @param arg0
     */

    @Override
    public void keyReleased(KeyEvent arg0) {
        int solicitudes = -1;
        String nombre = "";
        String apellido = "";
        String domicilio = "";
        String dni = "";
        String telefono = "";
        try {
            solicitudes = Integer.parseInt(this.textFieldLlamadas.getText());
            nombre = this.getNombre();
            apellido = this.getApellido();
            domicilio = this.getDomicilio();
            dni = this.getDni();
            telefono = this.getTelefono();
        } catch (NumberFormatException e) {
        }
        boolean condicion = (!nombre.isBlank() && !apellido.isBlank() && !domicilio.isBlank()
                && !dni.isBlank() && !telefono.isBlank() && solicitudes >= 0);
        this.btnAgregar.setEnabled(condicion);
        condicion = solicitudes >= 0;
        this.btnOperario.setEnabled(condicion);
    }

    /**
     * Metodo que carga el Set de asociados a la lista de la ventana.
     * <b>Post:</b> La lista de asociados presente en la vista queda actualizada.
     * @param asociados Set de asociados.
     */
    @Override
    public void actualizaLista(Set<Asociado> asociados) {
        Iterator<Asociado> it = asociados.iterator();
        Asociado asociado;

        while (it.hasNext()) {
            asociado = it.next();
            if (!this.modeloLista.contains(asociado)) {
                this.modeloLista.addElement(asociado);
                this.btnComenzar.setEnabled(true);
                this.setTextField();
            } else {
                this.modeloLista.remove(this.modeloLista.indexOf(asociado));
                if (this.modeloLista.isEmpty())
                    this.btnComenzar.setEnabled(false);
            }
            listAsociados.clearSelection();
        }
    }

    /**
     * Metodo que agrega un asociado, o lo remueve de la lista de asociados presente en la ventana.
     * @param asociado
     */
    @Override
    public void actualizaLista(Asociado asociado) {
        if (!this.modeloLista.contains(asociado)) {
            this.modeloLista.addElement(asociado);
            this.btnComenzar.setEnabled(true);
            this.setTextField();
        } else {
            this.modeloLista.remove(this.modeloLista.indexOf(asociado));
            if (this.modeloLista.isEmpty())
                this.btnComenzar.setEnabled(false);
        }
        listAsociados.clearSelection();
    }

    /**
     * Metodo que agrega la lista de pacientes de la clinica a la ventana de la Factura.
     */
    @Override
    public void actualizaListaPacientes() {
        for (Paciente p : Clinica.getInstance().getPacientes()){
            this.modeloPaciente.addElement(p);
        }
        this.listPacientes.clearSelection();
    }

    /**
     * Asocia el texto de la factura al area de texto de la ventana Factura.
     * @param sb
     */
    @Override
    public void muestraFactura(StringBuilder sb) {
        this.textoFactura.append(sb.toString());
    }

    /**
     * Borra el texto del TextArea.
     */
    @Override
    public void clearText() {
        this.textoFactura.setText("");
    }

    /**
     * Devuelve el paciente seleccionado.
     */
    @Override
    public Paciente getPacienteSelected() {
        return this.listPacientes.getSelectedValue();
    }

    /**
     * Activa la pestania de la simulacion.
     */
    @Override
    public void activaSimulacion() {
        this.tabbedPane.setEnabledAt(tabbedPane.indexOfTab("Simulacion"), true);
    }

    /**
     * Inicializa los TextField en vacio. Esto se realiza despues de agregar un asociado, o configurar el operario.
     */
    private void setTextField() {
        this.textFieldNombre.setText("");
        this.textFieldDni.setText("");
        this.textFieldApellido.setText("");
        this.textFieldTelefono.setText("");
        this.textFieldLlamadas.setText("");
        this.textFieldDomicilio.setText("");
        this.btnAgregar.setEnabled(false);
    }

    @Override
    public String getNombre() {
        return this.textFieldNombre.getText();
    }

    @Override
    public String getApellido() {
        return this.textFieldApellido.getText();
    }

    @Override
    public String getDni() {
        return this.textFieldDni.getText();
    }

    @Override
    public String getDomicilio() {
        return this.textFieldDomicilio.getText();
    }

    @Override
    public String getTelefono() {
        return this.textFieldTelefono.getText();
    }

    @Override
    public int getCantLlamadas() {
        return Integer.parseInt(this.textFieldLlamadas.getText());
    }

    @Override
    public void Visible(boolean cond) {
        setVisible(cond);
    }

    /**
     * Habilita el boton "Comenzar Simulacion".
     * @param cond
     */
    @Override
    public void setConfigurarVisibilidad(boolean cond) {
        this.btnComenzar.setEnabled(cond);
    }

    /**
     * Devuelve el asociado seleccionado en la lista.
     * @return
     */
    public Asociado getAsociadoSelected() {
        return this.listAsociados.getSelectedValue();
    }

    /**
     * Cuando se selecciona algun objeto de la lista, habilita los botones respectivos.
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.btnEliminar.setEnabled(!this.listAsociados.isSelectionEmpty());
        this.btnGenerar.setEnabled(!this.listPacientes.isSelectionEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
