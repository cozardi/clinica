package modelo.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import modelo.usuarios.Asociado;

public class ClinicaDTO implements Serializable
{
	private ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<AsociadoDTO>();
	private OperarioDTO operario;
	
	
	public ArrayList<AsociadoDTO> getAsociadosDTO() {
		return asociadosDTO;
	}
	public void setAsociadosDTO(ArrayList<AsociadoDTO> asociados) {
		this.asociadosDTO = asociados;
	}
	public OperarioDTO getOperarioDTO() {
		return operario;
	}
	public void setOperarioDTO(OperarioDTO operario) {
		this.operario = operario;
	}
	
	
}
