package lugares;

import exceptions.NoIngresaSalaPrivadaException;
import usuarios.Paciente;
import usuarios.PrioridadSala;

public class SalaEsperaPrivada{
    private Paciente pacienteActual;

    public void ingresaPaciente(Paciente ingresante) throws NoIngresaSalaPrivadaException
    {
        if (pacienteActual != null)
            if (pacienteActual.prioriza(ingresante))  //Si el paciente actual tiene prioridad sobre el ingresante
                throw new NoIngresaSalaPrivadaException();
            else
                pacienteActual = ingresante;
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

