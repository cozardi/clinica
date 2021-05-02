package clinica;

import usuarios.IMedico;
import usuarios.Medico;


import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * Esta clase contiene la informacion de los medicos, los pacientes y donde se encuentran los mismos
 */

public class Clinica 
{
	private static Clinica instance = null;
	//DATOS DE LA CLINICA
	private String nombre,direccion,telefono,ciudad;

	private Set<Medico> medicos;
	private PriorityQueue listaEspera;


	private Clinica(String nombre, String direccion, String telefono, String ciudad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		listaEspera = new PriorityQueue();
		medicos = new HashSet<>();
	}

	private Clinica() {
	}

	/**
	 * Crea la instancia de la clinica en caso de que esta no exista, sino devuelve la existente
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 * @param ciudad
	 * @return Una instancia de la clinica
	 */
	public static Clinica getInstance(String nombre, String direccion, String telefono, String ciudad){
		if (instance == null)
			instance = new Clinica(nombre,direccion,telefono,ciudad);
		return instance;
	}

	/**
	 *
	 * @return La instancia previamente creada (<i>null</i> en caso de no existir).
	 */
	public static Clinica getInstance(){
		return instance;
	}


	public void addMedico(Medico medico){
		medicos.add(medico);
	}




}
