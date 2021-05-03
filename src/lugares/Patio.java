package lugares;

import usuarios.Paciente;

import java.util.ArrayList;
import java.util.HashMap;

public class Patio{
    ArrayList<Paciente> pacientes;

    public void ingresaPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    public void retira(Paciente paciente){
        this.pacientes.remove(paciente);
    }


}
