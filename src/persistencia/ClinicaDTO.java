package persistencia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Clase con los datos a serializar de una Clinica
 *
 */
public class ClinicaDTO implements Serializable {

    private ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<AsociadoDTO>();
    private OperarioDTO operarioDTO;

    public ClinicaDTO() {
    }

    public ArrayList<AsociadoDTO> getAsociadosDTO() {
        return asociadosDTO;
    }

    public void setAsociadosDTO(ArrayList<AsociadoDTO> asociados) {
        this.asociadosDTO = asociados;
    }

    public OperarioDTO getOperarioDTO() {
        return operarioDTO;
    }

    public void setOperarioDTO(OperarioDTO operario) {
        this.operarioDTO = operario;
    }


}
