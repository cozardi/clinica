package lugares;

import exceptions.NoIngresaSalaPrivadaException;
import usuarios.Paciente;

/**
 *
 */

public class SalaEsperaPrivada{
    private Paciente pacienteActual;


    public void ingresaPaciente(Paciente ingresante) throws NoIngresaSalaPrivadaException
    {
        if (!this.vacia())
            if (pacienteActual.prioriza(ingresante))  //Si el paciente actual tiene prioridad por sobre el ingresante
                throw new NoIngresaSalaPrivadaException();
            else {
                SalaDeEspera.getinstance().getPatio().ingresaPaciente(pacienteActual);
                pacienteActual = ingresante;
            }
        else
            pacienteActual = ingresante;
    }

    public Paciente getPacienteActual(){
        return pacienteActual;
    }

    public boolean vacia(){
        return pacienteActual==null;
    }

    public void retira(){
        pacienteActual=null;
    }

}

