package persistencia;

import java.io.Serializable;
import java.util.ArrayList;

public class ClinicaDTO implements Serializable {

    private ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<AsociadoDTO>();
    private OperarioDTO operario;

    public ClinicaDTO() {
    }

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
