package modelo.persistencia;

import java.io.Serializable;

public class OperarioDTO implements Serializable {
	
	private int cantSolicitudes;

	public int getCantSolicitudes() {
		return cantSolicitudes;
	}

	public void setCantSolicitudes(int cantSolicitudes) {
		this.cantSolicitudes = cantSolicitudes;
	}
	
	

}
