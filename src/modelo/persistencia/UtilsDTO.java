package modelo.persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import modelo.ambulancia.Ambulancia;
import modelo.clinica.Clinica;
import modelo.clinica.Operario;
import modelo.usuarios.Asociado;

public class UtilsDTO {
	
    /**
     * Crea un OperarioDTO a partir de un Operario <br>
     * Pre:  operario dado no es null <br>
     * Post: se devuelve un OperarioDTO con los datos relevantes del Operario dado <br>
     *
     * @param operario un operario de ambulancia
     * @return un OperarioDTO con los datos relevantes del Operario dado
     */
	public static OperarioDTO OperarioAOperarioDTO(Operario operario)
	{
		var operarioDTO = new OperarioDTO();
		operarioDTO.setCantSolicitudes(operario.getCantSolicitud());
		
		return operarioDTO;
	}
	
	/**
	 * Crea un Operario a partir de un OperarioDTO y una Ambulancia <br>
	 * 
	 * Pre: operDTO no es null
	 * Post: devuelve un Operario con los datos de OperarioDTO y asignado a la ambulancia dada
	 * 
	 * @param operDTO clase para serializar Operarios
	 * @param ambulancia ambulancia a asociar al Operario que se devuelve
	 * 
	 * @return un Operario con los datos de OperarioDTO y la ambulancia dada
	 */
	public static Operario OperarioDTOAOperario(OperarioDTO operDTO, Ambulancia ambulancia)
	{
		Operario operarioNuevo = new Operario(ambulancia, operDTO.getCantSolicitudes());
		
		return operarioNuevo;
	}
	
	/**
     * Crea un AsociadoDTO a partir de un Asociado <br>
     * Pre:  Asociado dado no es null <br>
     * Post: se devuelve un AsociadoDTO con los datos relevantes del Asociado dado <br>
     *
     * @param asociado un asociado a la clinica
     * @return un AsociadoDTO con los datos relevantes del Asociado dado
     */
	public static AsociadoDTO AsociadoAAsociadoDTO(Asociado asociado)
	{
		var asociadoDTO = new AsociadoDTO();
		asociadoDTO.setCantSolicitudes(asociado.getCantSolicitudes());
		asociadoDTO.setDni(asociado.getDni());
		asociadoDTO.setDomicilio(asociado.getDomicilio());
		asociadoDTO.setNombre(asociado.getNombre());
		asociadoDTO.setTelefono(asociado.getTelefono());
		
		return asociadoDTO;
	}
	
	/**
	 * Crea un Asociado a partir de un AsociadoDTO y una Ambulancia <br>
	 * 
	 * Pre: asocDTO no es null
	 * Post: devuelve un Asociado con los datos de AsociadoDTO y asignado a la ambulancia dada
	 * 
	 * @param asocDTO clase para serializar Asociados
	 * @param ambulancia ambulancia a asociar al Asociado que se devuelve
	 * 
	 * @return un Asociado con los datos de AsociadoDTO y la ambulancia dada
	 */
	public static Asociado AsociadoDTOAAsociado(AsociadoDTO asocDTO, Ambulancia amb)
	{
		var asociadoNuevo = new Asociado(
				asocDTO.getDni(), 
				asocDTO.getNombre(), 
				asocDTO.getDomicilio(),
				asocDTO.getTelefono(),
				asocDTO.getCantSolicitudes(),
				amb);
		
		return asociadoNuevo;
	}
	
	/**
	 * Crea una ClinicaDTO a partir de la Clinica dada <br>
	 * 
	 * Pre: la Clinica dada no es null <br>
	 * Post: devuelve una ClinicaDTO con los datos relevantes de esta <br>
	 * 
	 * @param clinica la clinica de la que sacar los datos
	 * @return ClinicaDTO con los datos relevantes de la Clinica
	 */
	public static ClinicaDTO ClinicaAClinicaDTO(Clinica clinica)
	{
		ClinicaDTO clinicaDTO = new ClinicaDTO();
		ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<AsociadoDTO>();
		ArrayList<Asociado> asociados = new ArrayList<Asociado>(clinica.getAsociados());
		
		for (int i = 0; i < asociados.size(); i++)
			asociadosDTO.add(UtilsDTO.AsociadoAAsociadoDTO(asociados.get(i)));
		
		clinicaDTO.setOperarioDTO(UtilsDTO.OperarioAOperarioDTO(clinica.getOperario()));
		
		return clinicaDTO;
	}
	
	/**
	 * Cambia la instancia actual de la Clinica con los datos de una ClinicaDTO y una ambulancia dada
	 * 
	 * Pre: La ClinicaDTO no es null, ya hay una instancia de la Clinica activa
	 * Post: se cargan los datos de la ClinicaDTO en la instancia actual
	 * 
	 * @param clinDTO clase para serializar la Clinica
	 * @param amb
	 */
	public static void ClinicaDTOAClinica(ClinicaDTO clinDTO, Ambulancia amb)
	{
		Set<Asociado> asociados = new HashSet<Asociado>();
		for (int i = 0; i < clinDTO.getAsociadosDTO().size(); i++)
		{
			asociados.add(UtilsDTO.AsociadoDTOAAsociado(clinDTO.getAsociadosDTO().get(i), amb));
		}
		
		Clinica.getInstance().setAsociados(asociados);
		Clinica.getInstance().setOperario(UtilsDTO.OperarioDTOAOperario(clinDTO.getOperarioDTO(), amb));
	}

}
