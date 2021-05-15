package clinica;

import exceptions.DiasInvalidosException;
import exceptions.NoExisteException;
import exceptions.PacienteInvalidoException;
import factura.Factura;
import factura.Reporte;
import lugares.Habitacion;
import lugares.SalaDeEspera;
import usuarios.IMedico;
import usuarios.MedicoBase;
import usuarios.MedicoFactory;
import usuarios.Paciente;
import usuarios.PacienteFactory;

import java.util.*;

/**
 * Esta clase contiene la informacion de los medicos, los pacientes y donde se
 * encuentran los mismos
 */

public class Clinica {
	private static Clinica instance = null;
	// DATOS DE LA CLINICA

	private Set<IMedico> medicos = new TreeSet<>();
	private Set<Paciente> pacientes = new TreeSet<>();
	private PriorityQueue<Paciente> listaEspera;
	private ArrayList<Paciente> pacientesEnAtencion;

	public Set<IMedico> getMedicos() {
		return medicos;
	}

	public Set<Paciente> getPacientes() {
		return pacientes;
	}

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
	 * @param dni         String, contiene el DNI del paciente. <br>
	 * @param domicilio   String, contiene el Domicilio en nombre y número. <br>
	 * @param ciudad      String, representa la ciudad de nacimiento. <br>
	 * @param telefono    String, contiene el numero de telefono del paciente. <br>
	 * @param nombre      Nombre y Apellido del paciente. <br>
	 * @param rangoEtario Rango etario del paciente (nino, joven o mayor) <br>
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
	public Paciente buscaPaciente(int numeroPaciente) {
		for (Paciente paciente : pacientesEnAtencion) {
			if (paciente.getNumero() == numeroPaciente) {
				return paciente;
			}
		}
		return null;
	}

	/**
	 * Busca el medico con cierto numero de medico
	 * 
	 * @param numeroMedico: El numero del medico
	 * @return Retorna el Medico con ese numero o null si no existe
	 */
	public IMedico buscaMedico(int numeroMedico) {
		for (IMedico medico : medicos) {
			if (medico.getNumero() == numeroMedico) {
				return medico;
			}
		}
		return null;
	}

	/**
	 * Agrega una consulta medica al paciente <br>
	 * 
	 * <b>pre</b> El paciente y medico dados no son nulos <br>
	 * <b>post</b> La consulta queda registrada en el paciente y el reporte del
	 * medico <br>
	 * 
	 * @param paciente Paciente que recibio la consulta <br>
	 * @param medico   Medico que hizo la consulta <br>
	 * @throws PacienteInvalidoException si el medico o paciente son nulos <br>
	 */
	public void agregaConsultaAPaciente(Paciente paciente, IMedico medico) throws Exception {
		// TODO: integrar con modulo de Reporte de Actividad Medica
		if (paciente != null) {
			paciente.AgregaConsulta(medico);
		} else {
			throw new PacienteInvalidoException("Error. Paciente es nulo");
		}
	}

	/**
	 * Agregamos una consulta al historial de consultas del paciente Pre: El numero
	 * de paciente debe ser un valor existente<br>
	 * El Imedico no debe ser null <br>
	 * Post: retorna true si se pudo agregar con exito y false en caso contrario
	 * 
	 * @param numero
	 * @param medico
	 * @return
	 * @throws Exception
	 */
	public boolean agregaConsultaAPaciente(int numero, IMedico medico) throws Exception {
		Paciente paciente = buscaPaciente(numero);
		if (paciente != null) // lo encontro
		{
			agregaConsultaAPaciente(paciente, medico);

			return true;
		} else
			return false;
	}

	/**
	 * Agrega una estadia en una habitacion al paciente<br>
	 * 
	 * <b>pre:</b> El paciente y hab dados no son nulos<br>
	 * <b>post:</b> La consulta queda registrada en el paciente y el reporte del
	 * medico<br>
	 * 
	 * @param paciente: Paciente internado
	 * @param hab:      Habitacion en la que estuvo el paciente
	 * @param dias:     dias de estadia en la habitacion
	 * @throws Exception si el medico o paciente son nulos
	 */
	public void agregaInternacionAPaciente(Paciente paciente, Habitacion hab, int dias) throws Exception {
		if (paciente != null) {
			paciente.AgregaInternacion(hab, dias);
		} else {
			throw new PacienteInvalidoException("Error. Paciente es nulo");
		}
	}

	/**
	 * Agrega una estadia en una habitacion al paciente<br>
	 * 
	 * 
	 * @param numero el numero de paciente al que se le quiere agregar la
	 *               internacion
	 * @param hab    la habitacion que se le agregara
	 * @param dias   los dias que estuvo en dicha habitacion
	 * @return verdadero si se encontro el paciente con el numero, falso si no
	 * @throws Exception si la habitacion o los dias no son validos
	 */
	public boolean agregaInternacionAPaciente(int numero, Habitacion hab, int dias) throws Exception {
		Paciente paciente = buscaPaciente(numero);
		if (paciente != null) // lo encontro
		{
			agregaInternacionAPaciente(paciente, hab, dias);

			return true;
		} else
			return false;
	}

	/**
	 * Pre:El paciente y la fecha no deben ser null<br>
	 * Post:Muestra la tabla de la facura y tambien agrega la informacion necesaria
	 * a los reportes de los medicos
	 * 
	 * @param paciente
	 * @param fecha
	 * @throws PacienteInvalidoException
	 */
	public void imprimeFacturaDePaciente(Paciente paciente, GregorianCalendar fecha) throws PacienteInvalidoException {
		

		Factura facturaNueva = new Factura(paciente, fecha);

		facturaNueva.ImprimeFactura();
		// Por cada medico en el paciente
		IMedico it2;
		Set<IMedico> keys = paciente.getConsultas().keySet();
		Iterator<IMedico> it = keys.iterator();
		while (it.hasNext()) {
			it2 = it.next();
			it2.getReporte().add(new Reporte(facturaNueva.getFecha(), paciente.getNombre(),
					paciente.getConsultas().get(it2), it2.getHonorario() * paciente.getConsultas().get(it2)));
		}
		paciente.ReseteaPrestaciones();
	}
}
