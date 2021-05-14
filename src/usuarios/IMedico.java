package usuarios;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeSet;

import exceptions.FechaInvalidaException;
import factura.Reporte;

/**
 * Interfaz de medico, define el calculo de Honorario de cada tipo de medico
 */
public abstract class IMedico extends Usuarios {
   protected double Honorario;
   protected TreeSet<Reporte> reporte = new TreeSet<Reporte>();

   public IMedico(String dni, String domicilio, String ciudad, String telefono, String nombre, int numero,
         double honorarioBasico) {
      super(dni, domicilio, ciudad, telefono, nombre, numero);
      this.Honorario = honorarioBasico;
   }

   /**
    * Obtiene el Honorario del medico generado
    * 
    * @return honorario con su aumento dependiendo de su especialidad, tipo de
    *         contratacion y posgrado
    */
   public abstract void setHonorario();

   public double getHonorario() {
      return Honorario;
   }

   public TreeSet<Reporte> getReporte() {
      return reporte;
   }

   public void muestraReporte(GregorianCalendar f1, GregorianCalendar f2) throws FechaInvalidaException {
      double total = 0;
      Iterator<Reporte> it = reporte.iterator();
      Reporte[] aux = reporte.toArray(new Reporte[reporte.size()]);

      // it.next().getFecha().compareTo(f1) < 0
      System.out.println(this.nombre);
      // While que busca f1 en adelante
      int i = 0;
      while (i < aux.length && aux[i].getFecha().compareTo(f1) < 0) {
         i++;
      }
      if (i < aux.length) {
         while (i < aux.length && aux[i].getFecha().compareTo(f2) <= 0) {
            System.out.println(aux[i]);
            total += aux[i].getSubtotal();
            i++;
         }
         System.out.println("El total generado por este medico es: " + total);
      } else {
         throw new FechaInvalidaException("No se ha encontrado reportes a partir de la fecha ingresada\n");
      }

   }

}
