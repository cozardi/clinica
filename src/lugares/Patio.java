package lugares;

import usuarios.Paciente;

import java.util.ArrayList;

public class Patio{
    ArrayList<Paciente> pacientes = new ArrayList<>();

    public void ingresaPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    public void retira(Paciente paciente){
        this.pacientes.remove(paciente);
    }


}
