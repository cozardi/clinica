package persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import modelo.ambulancia.Ambulancia;
import modelo.clinica.Clinica;
import modelo.clinica.Operario;
import modelo.usuarios.Asociado;

public class UtilsDTO {

    public static OperarioDTO OperarioAOperarioDTO(Operario operario) {
        var operarioDTO = new OperarioDTO();
        operarioDTO.setCantSolicitudes(operario.getCantSolicitud());

        return operarioDTO;
    }

    public static Operario OperarioDTOAOperario(OperarioDTO operDTO, Ambulancia ambulancia) {
        Operario operarioNuevo = new Operario(ambulancia, operDTO.getCantSolicitudes());

        return operarioNuevo;
    }

    public static AsociadoDTO AsociadoAAsociadoDTO(Asociado asociado) {
        var asociadoDTO = new AsociadoDTO();
        asociadoDTO.setCantSolicitudes(asociado.getCantSolicitudes());
        asociadoDTO.setDni(asociado.getDni());
        asociadoDTO.setDomicilio(asociado.getDomicilio());
        asociadoDTO.setNombre(asociado.getNombre());
        asociadoDTO.setTelefono(asociado.getTelefono());

        return asociadoDTO;
    }

    public static Asociado AsociadoDTOAAsociado(AsociadoDTO asocDTO, Ambulancia amb) {
        Asociado asociadoNuevo = new Asociado(
                asocDTO.getDni(),
                asocDTO.getNombre(),
                asocDTO.getDomicilio(),
                asocDTO.getTelefono(),
                asocDTO.getCantSolicitudes(),
                amb);

        return asociadoNuevo;
    }

    public static ClinicaDTO ClinicaAClinicaDTO(Clinica clinica) {
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<AsociadoDTO>();
        ArrayList<Asociado> asociados = new ArrayList<Asociado>(clinica.getAsociados());

        for (int i = 0; i < asociados.size(); i++)
            asociadosDTO.add(UtilsDTO.AsociadoAAsociadoDTO(asociados.get(i)));

        clinicaDTO.setAsociadosDTO(asociadosDTO);
        clinicaDTO.setOperarioDTO(UtilsDTO.OperarioAOperarioDTO(clinica.getOperario()));
        return clinicaDTO;
    }

    public static void ClinicaDTOAClinica(ClinicaDTO clinDTO, Ambulancia amb) {
        Set<Asociado> asociados = new HashSet<Asociado>();
        for (int i = 0; i < clinDTO.getAsociadosDTO().size(); i++) {
            asociados.add(UtilsDTO.AsociadoDTOAAsociado(clinDTO.getAsociadosDTO().get(i), amb));
        }
        Clinica.getInstance().setAsociados(asociados);
        Clinica.getInstance().setOperario(UtilsDTO.OperarioDTOAOperario(clinDTO.getOperarioDTO(), amb));
    }
}
