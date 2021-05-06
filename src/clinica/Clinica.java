package clinica;

import exceptions.NoExisteException;
import lugares.SalaDeEspera;
import usuarios.IMedico;
import usuarios.MedicoFactory;
import usuarios.Paciente;
import usuarios.PacienteFactory;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Esta clase contiene la informacion de los medicos, los pacientes y donde se
 * encuentran los mismos
 */

public class Clinica {
	private static Clinica instance = null;
	// DATOS DE LA CLINICA
	private String nombre, direccion, telefono, ciudad;

	private Set<IMedico> medicos;
	private Set<Paciente> pacientes;
	private PriorityQueue<Paciente> listaEspera;
	private ArrayList<Paciente> pacientesEnAtencion;

	private Clinica(String nombre, String direccion, String telefono, String ciudad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		listaEspera = new PriorityQueue();
		medicos = new TreeSet<>();
		pacientes = new TreeSet<>();
		pacientesEnAtencion = new ArrayList<>();
	}

	private Clinica() {
	}

	/**
	 * Crea la instancia de la clinica en caso de que esta no exista, sino devuelve
	 * la existente
	 * 
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 * @param ciudad
	 * @return Una instancia de la clinica
	 */
	public static Clinica getInstance(String nombre, String direccion, String telefono, String ciudad) {
		if (instance == null)
			instance = new Clinica(nombre, direccion, telefono, ciudad);
		return instance;
	}

	/**
	 *
	 * @return La instancia previamente creada (<i>null</i> en caso de no existir).
	 */
	public static Clinica getInstance() {
		return instance;
	}

	/**
	 * Agrega un medico a la lista de medicos de la Clinica, en caso de ingresar un
	 * dato erroneo informa con un mensaje y no crea el objeto
	 * 
	 * @param dni
	 * @param domicilio
	 * @param ciudad
	 * @param telefono
	 * @param nombre
	 * @param numero
	 * @param honorarioBasico
	 * @param especialidad
	 * @param tipoContratacion
	 */
	public void addMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,
			int honorarioBasico, String especialidad, String tipoContratacion) {
		try {
			IMedico medico = MedicoFactory.getMedico(dni, domicilio, ciudad, telefono, nombre, numero, honorarioBasico,
					especialidad, tipoContratacion);
		} catch (NoExisteException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,
			int honorarioBasico, String especialidad, String tipoContratacion, String posgrado) {
		try {
			IMedico medico = MedicoFactory.getMedico(dni, domicilio, ciudad, telefono, nombre, numero, honorarioBasico,
					especialidad, tipoContratacion, posgrado);
			medicos.add(medico);
		} catch (NoExisteException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Ingresa el paciente a la clinica y lo deriva a la sala de espera. En caso de
	 * no poder crearlo informa el error con un mensaje.
	 * 
	 * @param dni
	 * @param domicilio
	 * @param ciudad
	 * @param telefono
	 * @param nombre
	 * @param numHistClinica
	 * @param rangoEtario
	 */
	public void ingresaPaciente(String dni, String domicilio, String ciudad, String telefono, String nombre,
			int numHistClinica, String rangoEtario) {
		try {
			Paciente paciente = PacienteFactory.getPaciente(dni, domicilio, ciudad, telefono, nombre, numHistClinica,
					rangoEtario);
			pacientes.add(paciente); // si el paciente ya se encuentra no se agrega a la estructura (por implementar
										// la interfaz Set)
			listaEspera.add(paciente);
			SalaDeEspera.getinstance().ingresaSala(paciente);
		} catch (NoExisteException e) {
			System.out.println(e.getMessage());
		}
	}

	public void atiendePaciente() {
		Paciente paciente = listaEspera.poll();
		SalaDeEspera.getinstance().retiraPaciente(paciente);
		pacientesEnAtencion.add(paciente);
	}

}
