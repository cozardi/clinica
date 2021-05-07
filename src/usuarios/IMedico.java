package usuarios;

/**
 * Interfaz de medico, define el calculo de Honorario de cada tipo de medico
 */
public interface IMedico {

   /**
    * Obtiene el Honorario del medico generado
    * @return honorario con su aumento dependiendo de su especialidad, tipo de contratacion y posgrado
    */
   double getHonorario();

}
