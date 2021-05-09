package clinica;

import exceptions.NoExisteException;
import exceptions.PacienteInvalidoException;
import lugares.Habitacion;
import lugares.SalaDeEspera;
import usuarios.IMedico;
import usuarios.Medico;
import usuarios.MedicoFactory;
import usuarios.Paciente;
import usuarios.PacienteFactory;

import java.util.ArrayList;
import java.util.HashSet;
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

	private Set<IMedico> medicos = new HashSet<IMedico>();
	private Set<Paciente> pacientes = new HashSet<Paciente>();
	private PriorityQueue<Paciente> listaEspera;
	private ArrayList<Paciente> pacientesEnAtencion;

	/**
	 * Constructor privado, es invocado por el metodo <i>getInstance</i>
	 * 
	 * @param nombre    Representa el nombre de la clinica
	 * @param direccion Contiene la direccion de la clinica
	 * @param telefono  Contiene el telefono de la clinica
	 * @param ciudad    Contiene la ciudad donde se encuentra la clinica
	 */
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
	 * @param nombre    Representa el nombre de la clinica
	 * @param direccion Contiene la direccion de la clinica
	 * @param telefono  Contiene el telefono de la clinica
	 * @param ciudad    Contiene la ciudad donde se encuentra la clinica
	 * @return Una instancia de la clinica
	 */
	public static Clinica getInstance(String nombre, String direccion, String telefono, String ciudad) {
		if (instance == null)
			instance = new Clinica(nombre, direccion, telefono, ciudad);
		return instance;
	}

	/**
	 * <b>Pre: </b> Deberia invocarse anteriormente al otro constructor para generar
	 * la primer instancia de la clinica, luego usar constructor sin parametros.
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
	 * @param dni              Contiene el DNI del medico
	 * @param domicilio        contiene el domicilio del medico
	 * @param ciudad           representa la ciudad de origen
	 * @param telefono         contiene el telefono del medico
	 * @param nombre           Nombre y apellido del medico
	 * @param numero           representa el numero de licencia medico (unico por
	 *                         medico)
	 * @param honorarioBasico  Honorario basico del medico
	 * @param especialidad     especialidad del medico (clinico, cirujano, pediatra)
	 * @param tipoContratacion tipo de contratacion (permanente, temporario)
	 */
	public void addMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,
			int honorarioBasico, String especialidad, String tipoContratacion) {
		try {
			IMedico medico = MedicoFactory.getMedico(dni, domicilio, ciudad, telefono, nombre, numero, honorarioBasico,
					especialidad, tipoContratacion);
			medicos.add(medico);// tira error aca?-->si
		} catch (NoExisteException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Agrega un medico a la lista de medicos de la Clinica, en caso de ingresar un
	 * dato erroneo informa con un mensaje y no crea el objeto
	 * 
	 * @param dni              Contiene el DNI del medico
	 * @param domicilio        contiene el domicilio del medico
	 * @param ciudad           representa la ciudad de origen
	 * @param telefono         contiene el telefono del medico
	 * @param nombre           Nombre y apellido del medico
	 * @param numero           representa el numero de licencia medico (unico por
	 *                         medico)
	 * @param honorarioBasico  Honorario basico del medico
	 * @param especialidad     especialidad del medico (clinico, cirujano, pediatra)
	 * @param tipoContratacion tipo de contratacion (permanente, temporario)
	 * @param posgrado         posgrado del medico en caso de tenerlo (magister,
	 *                         doctorado)
	 */
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
	 * @param dni            String, contiene el DNI del paciente. <br>
	 * @param domicilio      String, contiene el Domicilio en nombre y número. <br>
	 * @param ciudad         String, representa la ciudad de nacimiento. <br>
	 * @param telefono       String, contiene el numero de telefono del paciente.
	 *                       <br>
	 * @param nombre         Nombre y Apellido del paciente. <br>
	 * @param numHistClinica valor autoincremental asignado por la clinica,
	 *                       representa su numero de Historia Clinica. <br>
	 * @param rangoEtario    Rango etario del paciente (nino, joven o mayor) <br>
	 */

	public void ingresaPaciente(String dni, String domicilio, String ciudad, String telefono, String nombre,
			String rangoEtario) {
		try {
			Paciente paciente = PacienteFactory.getPaciente(dni, domicilio, ciudad, telefono, nombre, rangoEtario);
			pacientes.add(paciente); // si el paciente ya se encuentra no se agrega a la estructura (por implementar
										// la interfaz Set)
			listaEspera.add(paciente);
			SalaDeEspera.getinstance().ingresaSala(paciente);
		} catch (NoExisteException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Mueve el paciente de la lista de espera, lo retira de la sala de espera
	 * correspondiente y lo ingresa en la lista de pacientes en atencion.<br>
	 * En caso de no haber pacientes en la lista de espera, no realiza ningun
	 * movimiento.
	 */
	public void atiendePaciente() {
		Paciente paciente = listaEspera.poll();
		if (paciente != null) {
			SalaDeEspera.getinstance().retiraPaciente(paciente);
			pacientesEnAtencion.add(paciente);
		}
	}

	/**
	 * Busca el paciente con cierto numero de paciente
	 * 
	 * @param numeroPaciente: El numero de historia clinica del paciente a buscar
	 * @return Retorna el paciente con ese numero o null si no existe
	 */
	private Paciente buscaPaciente(int numeroPaciente) {
		for (Paciente paciente : pacientesEnAtencion) {
			if (paciente.getNumero() == numeroPaciente) {
				return paciente;
			}
		}
		return null;
	}

	/**
	 * Agrega una consulta m�dica al paciente
	 * 
	 * <b>pre:</b> El paciente y medico dados no son nulos <b>post:</b> La consulta
	 * queda registrada en el paciente y el reporte del m�dico
	 * 
	 * @param paciente: Paciente que recibio la consulta
	 * @param medico:   Medico que hizo la consulta
	 * @throws PacienteInvalidoException si el medico o paciente son nulos
	 */
	public void agregaConsultaAPaciente(Paciente paciente, Medico medico) throws Exception {
		// TODO: integrar con m�dulo de Reporte de Actividad Medica
		if (paciente != null) {
			paciente.AgregaConsulta(medico);
		} else {
			throw new PacienteInvalidoException("Error. Paciente es nulo");
		}
	}

	/**
	 * Agrega una estad�a en una habitacion al paciente<br>
	 * 
	 * <b>pre:</b> El paciente y medico dados no son nulos<br>
	 * <b>post:</b> La consulta queda registrada en el paciente y el reporte del
	 * m�dico<br>
	 * 
	 * @param paciente:   Paciente internado
	 * @param habitacion: Habitacion en la que estuvo el paciente
	 * @param dias:       dias de estadia en la habitacion
	 * @throws PacienteInvalidoException si el medico o paciente son nulos
	 */
	public void agregaInternacionAPaciente(Paciente paciente, Habitacion hab, int dias) throws Exception {

		if (paciente != null) {
			paciente.AgregaInternacion(hab, 0);
		} else {
			throw new PacienteInvalidoException("Error. Paciente es nulo");
		}
	}

}
