package modelo.clinica;

import modelo.ambulancia.Ambulancia;
import modelo.exceptions.*;
import modelo.factura.Factura;
import modelo.factura.Reporte;
import modelo.lugares.Habitacion;
import modelo.lugares.SalaDeEspera;
import modelo.usuarios.*;

import java.util.*;

/**
 * Esta clase contiene la informacion de los medicos, los pacientes y donde se
 * encuentran los mismos. <br>
 * Tambien contiene una lista de asociados, y el operario de la ambulancia .<br>
 */

public class Clinica {
    private static Clinica instance = null;
    // DATOS DE LA CLINICA
    private String nombre = "Hospital", direccion = "Avenida siempre viva", telefono = "123123", ciudad = "Balcarce";

    private Set<Medico> medicos = new TreeSet<>();
    private Set<Paciente> pacientes = new TreeSet<>();
    private Set<Asociado> asociados = new HashSet<>();
    private PriorityQueue<Paciente> listaEspera = new PriorityQueue<Paciente>();
    private ArrayList<Paciente> pacientesEnAtencion = new ArrayList<Paciente>();
    private Operario operario = new Operario(Ambulancia.get_instance(), 0);


    public Set<Medico> getMedicos() {
        return medicos;
    }

	public Set<Paciente> getPacientes() {
        return pacientes;
    }

    private Clinica() {

    }

    //    /**
//     * Crea la instancia de la modelo.clinica en caso de que esta no exista, sino devuelve
//     * la existente
//     *
//     * @param nombre    Representa el nombre de la modelo.clinica
//     * @param direccion Contiene la direccion de la modelo.clinica
//     * @param telefono  Contiene el telefono de la modelo.clinica
//     * @param ciudad    Contiene la ciudad donde se encuentra la modelo.clinica
//     * @return Una instancia de la modelo.clinica
//     */

    public static Clinica getInstance() {
        if (instance == null)
            instance = new Clinica();
        return instance;
    }

    /**
     * <b>Pre: </b> Deberia invocarse anteriormente al otro constructor para generar
     * la primer instancia de la modelo.clinica, luego usar constructor sin parametros.
     *
     * @return La instancia previamente creada (<i>null</i> en caso de no existir).
     */
//    public static Clinica getInstance() {
//        return instance;
//    }

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
                          int honorarioBasico, String especialidad, String tipoContratacion,String posgrado) {
        try {
            Medico medico = MedicoFactory.getMedico(dni, domicilio, ciudad, telefono, nombre, numero, honorarioBasico,
                    especialidad, tipoContratacion, posgrado);
            medicos.add(medico);
        } catch (NoExisteException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Agrega un asociado a la estructura de Asociados. Si ya existe arroja excepcion.
     *
     * @param asociado
     * @throws YaExisteAsociadoException El controlador recibe esta excepci??n y delega a la vista la soluci??n.
     */
    public void addAsociado(Asociado asociado) throws YaExisteAsociadoException {
        if (!this.asociados.add(asociado))
            throw new YaExisteAsociadoException("El Asociado ingresado ya existe");
    }

    /**
     * Elimina un determinado asociado de la lista de asociados. Si el asociado no existe,
     * lanza una excepcion <br>
     * <b>Pre:</b> El asociado pasado por parametro no debe ser nulo.
     * <b>Post: </b> Si existe el asociado, lo elimina del Set de asociados.
     * @param asociado Asociado de la clinica.
     */
    public void eliminaAsociado(Asociado asociado) throws NoExisteAsociadoException {
        if (!this.asociados.remove(asociado))
            throw new NoExisteAsociadoException("El asociado no existe");
    }

    /**
     * Setea los asociados a partir de un set pasado por parametro<br>
     * <b>Pre: </b> Debe existir la collecion pasada por parametro.
     * <b>Post:</b> La clinica tiene una nueva lista de asociados.
     * @param asociados
     */
    public void setAsociados(Set<Asociado> asociados) {
        this.asociados = asociados;
	}
    
    /**
     * Crea el operario de la ambulancia.
     * <b>Post: </b> La clinica tiene su operario asignado.
     * @param cantSolicitudes Cantidad de solicitudes que tiene el operario.
     */
    public void setOperario(int cantSolicitudes) {
        this.operario = new Operario(Ambulancia.get_instance(), cantSolicitudes);
    }


    public void setOperario(Operario operario)
    {
    	this.operario = operario;
    }


    public Operario getOperario() {
        return this.operario;
    }

    public Set<Asociado> getAsociados() {
        return asociados;
    }

    /**
     * Ingresa el paciente a la Clinica y lo deriva a la sala de espera. En caso de
     * no poder crearlo informa el error con un mensaje.
     *
     * @param dni         String, contiene el DNI del paciente. <br>
     * @param domicilio   String, contiene el Domicilio en nombre y n??mero. <br>
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
     * @param numeroPaciente El numero de historia modelo.clinica del paciente a buscar
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
    public Medico buscaMedico(int numeroMedico) {
        for (Medico medico : medicos) {
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
     */
    public void agregaConsultaAPaciente(Paciente paciente, Medico medico) {
        if (paciente != null) {
            paciente.AgregaConsulta(medico);
        }
    }

    /**
     * Agregamos una consulta al historial de consultas del paciente <br>
     * <b>Pre:</b> El IMedico no debe ser null <br>
     * <b>Post:</b> si encontro al paciente y se agrego con exito returna <i>true</i>, en caso contrario <i>false</i>
     *
     * @param numero El numero de paciente del
     * @param medico
     * @return boolean, <i>true</i> si encontro al paciente y <i>false</i> si no
     */
    public boolean agregaConsultaAPaciente(int numero, Medico medico) {
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
     * @param dias:     Dias de estadia en la habitacion
     * @throws DiasInvalidosException si la cantidad de dias es menor que 0
     */
    public void agregaInternacionAPaciente(Paciente paciente, Habitacion hab, int dias) throws DiasInvalidosException {
        if (dias > 0) {
            paciente.AgregaInternacion(hab, dias);
        } else {
            throw new DiasInvalidosException("Error. Cantidad de dias no valida,", dias);
        }
    }

    /**
     * Agrega una estadia en una habitacion al paciente<br>
     *
     * <b>pre:</b> El paciente y hab dados no son nulos<br>
     * <b>post:</b> La consulta queda registrada en el paciente y el reporte del
     *
     * @param numero el numero de paciente al que se le quiere agregar la
     *               internacion
     * @param hab    la habitacion que se le agregara
     * @param dias   los dias que estuvo en dicha habitacion
     * @return verdadero si se encontro el paciente con el numero, falso si no
     * @throws DiasInvalidosException si la cantidad de dias es menor que 0
     */
    public boolean agregaInternacionAPaciente(int numero, Habitacion hab, int dias) throws DiasInvalidosException {
        Paciente paciente = buscaPaciente(numero);
        if (paciente != null) // lo encontro
        {
            agregaInternacionAPaciente(paciente, hab, dias);

            return true;
        } else
            return false;
    }

    /**
     * Genera la factura del paciente, y la devuelve en un String Builder.
     * <b>Pre:</b>El paciente y la fecha no deben ser null<br>
     * <b>Post:</b>Muestra la tabla de la factura y tambien agrega la informacion necesaria
     * a los reportes de los medicos
     *
     * @param paciente
     * @param fecha
     * @throws PacienteInvalidoException
     */

    public StringBuilder imprimeFacturaDePaciente(Paciente paciente, GregorianCalendar fecha) throws PacienteInvalidoException {


        Factura facturaNueva = new Factura(paciente, fecha);

        StringBuilder sb = facturaNueva.ImprimeFactura();
        // Por cada medico en el paciente
        Medico it2;
        Set<Medico> keys = paciente.getConsultas().keySet();
        Iterator<Medico> it = keys.iterator();
        while (it.hasNext()) {
            it2 = it.next();
            it2.getReporte().add(new Reporte(facturaNueva.getFecha(), paciente.getNombre(),
                    paciente.getConsultas().get(it2), it2.getHonorario() * paciente.getConsultas().get(it2)));
        }
        paciente.ReseteaPrestaciones();
        return sb;
    }

    /**
     * Crea los threads de los asociados y del operario. Recorre el Set de asociados
     * y da la orden para que se inicien los hilos. <br>
     * <b>Post:</b> Todos los hilos estan listos para su ejecucion.
     */
    public void simulacion() {
        Thread op = new Thread(this.operario);
        for (Asociado asociado : this.asociados) {
            Thread h = new Thread(asociado);
            h.start();
        }
        op.start();
    }
}
