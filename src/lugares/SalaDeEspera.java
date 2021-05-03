package lugares;

import exceptions.NoIngresaSalaPrivadaException;
import usuarios.Paciente;

/**
 * Clase que contiene la Sala de Espera Privada y el patio, se encarga de acomodar los nuevos pacientes a la sala que le corresponda. <br>
 */


public class SalaDeEspera {
    private static SalaDeEspera _instance=null;
    private Patio patio=new Patio();
    private SalaEsperaPrivada salaprivada=new SalaEsperaPrivada();

    private SalaDeEspera(){
    }

    public static SalaDeEspera getinstance(){
        if (_instance==null)
            _instance=new SalaDeEspera();
        return _instance;
    }

    /**
     * Metodo que se encarga de retirar al paciente invocado de la sala de espera privada o del patio. <br>
     * <b>Pre: </b> El paciente debe existir, y debe estar en la lista de espera. <br>
     * <b>Post: </b> El paciente es retirado de la lista del patio, o de la sala de espera privada. <br>
     * @param paciente
     */

    public void retiraPaciente(Paciente paciente){
        if (salaprivada.getPacienteActual().equals(paciente))
            salaprivada.retira();
        else
            patio.retira(paciente);
    }


    /**
     * Metodo que se encarga de ingresar al paciente. Primero intenta ingresarlo en la sala privada, si no es posible lo mueve al patio.<br>
     * <b>Pre:  </b> El paciente debe existir. <br>
     * <b>Post: </b> El paciente es acomodado a la sala correspondiente. <br>
     * @param paciente
     */
    public void ingresaSala(Paciente paciente){
        try{
            salaprivada.ingresaPaciente(paciente);
        } catch (NoIngresaSalaPrivadaException e) {
            patio.ingresaPaciente(paciente);
        }
    }

}
