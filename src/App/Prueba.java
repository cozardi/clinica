package App;

import Exceptions.NoExisteException;
import Usuarios.IMedico;
import Usuarios.MedicoFactory;

public class Prueba {

    public static void main(String[] args) {
        IMedico medico;
            try {
                medico = MedicoFactory.getMedico("234565","Marconi 2345","Mar del Plata","223456732","Mario Pergollini",1406,3000,"Marianito","Permanente");
                System.out.println(medico.getHonorario());
            } catch (NoExisteException e) {
                System.out.println(e.getMessage());
            }


    }
}
