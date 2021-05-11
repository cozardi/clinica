package usuarios;

/**
 * Interfaz de medico, define el calculo de Honorario de cada tipo de medico
 */
public abstract class IMedico extends Usuarios{
   protected double Honorario;

   public IMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero, double honorarioBasico) {
      super(dni, domicilio, ciudad, telefono, nombre, numero);
      this.Honorario = honorarioBasico;
   }

   /**
    * Obtiene el Honorario del medico generado
    * @return honorario con su aumento dependiendo de su especialidad, tipo de contratacion y posgrado
    */
   public abstract void setHonorario();

   public double getHonorario() {
      return Honorario;
   }
}
