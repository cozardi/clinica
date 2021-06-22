package vista;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListSelectionModel;


import java.awt.event.*;

import modelo.usuarios.Asociado;

public class VentanaInicio extends JFrame implements IVista, IVistaFactura ,KeyListener, ListSelectionListener {
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
	private JPanel panelSerial;
	private JPanel panelGuardar;
	private JButton btnGuardarDatos;
	private JPanel panelCargar;
	private JButton btnCargar;
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
	private DefaultListModel<Asociado> modeloLista= new DefaultListModel<Asociado>();
	private JTabbedPane tabbedPane;
	private JPanel panelContenedorSimulacion;

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
		panelCentral.setLayout(new GridLayout(5, 1, 0, 0));
		
		panelAgregar = new JPanel();
		panelCentral.add(panelAgregar);
		panelAgregar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		
		panelSerial = new JPanel();
		panelCentral.add(panelSerial);
		panelSerial.setLayout(new GridLayout(1, 2, 0, 0));
		
		panelGuardar = new JPanel();
		panelSerial.add(panelGuardar);
		
		btnGuardarDatos = new JButton("Guardar");
		panelGuardar.add(btnGuardarDatos);
		
		panelCargar = new JPanel();
		panelSerial.add(panelCargar);
		
		btnCargar = new JButton("Cargar ");
		panelCargar.add(btnCargar);
		
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
		
		lblDni = new JLabel("Dni:");
		panelDni.add(lblDni);
		
		textFieldDni = new JTextField();
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
		tabbedPane.setEnabledAt(tabbedPane.indexOfTab("Simulacion"),false);


		this.setVisible(true);
	}

	@Override
	public void addActionListener(ActionListener actionListener) {
		if (!tieneActionListener) {
			this.btnAgregar.addActionListener(actionListener);
			this.btnComenzar.addActionListener(actionListener);
			this.btnCargar.addActionListener(actionListener);
			this.btnEliminar.addActionListener(actionListener);
			this.btnGuardarDatos.addActionListener(actionListener);
			this.btnCargar.addActionListener(actionListener);
			tieneActionListener = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent arg0)
	{
		int solicitudes = -1;
		String nombre = "";
		String apellido = "";
		String domicilio = "";
		String dni = "";
		String telefono = "";
		try{
			solicitudes = Integer.parseInt(this.textFieldLlamadas.getText());
			nombre = this.getNombre();
			apellido = this.getApellido();
			domicilio = this.getDomicilio();
			dni = this.getDni();
			telefono = this.getTelefono();
		}
		catch (NumberFormatException e) {
		}
		boolean condicion = (!nombre.isBlank() && !apellido.isBlank() && !domicilio.isBlank()
							&& !dni.isBlank() && !telefono.isBlank() && solicitudes>=0);
		this.btnAgregar.setEnabled(condicion);
		condicion = solicitudes>=0;
			this.btnOperario.setEnabled(condicion);
	}

	@Override
	public void actualizaLista(Asociado asociado) {
		if (!this.modeloLista.contains(asociado)) {
			this.modeloLista.addElement(asociado);
			this.btnComenzar.setEnabled(true);
			this.setTextField();
		}
		else {
			this.modeloLista.remove(this.modeloLista.indexOf(asociado));
			if (this.modeloLista.isEmpty())
				this.btnComenzar.setEnabled(false);
		}
		listAsociados.clearSelection();
	}

	@Override
	public void activaSimulacion() {
		this.tabbedPane.setEnabledAt(tabbedPane.indexOfTab("Simulacion"),true);
	}

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

	public Asociado getAsociadoSelected(){
		return this.listAsociados.getSelectedValue();
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.btnEliminar.setEnabled(!this.listAsociados.isSelectionEmpty());
	}
}
