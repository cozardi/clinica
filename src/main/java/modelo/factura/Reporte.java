package modelo.factura;

import java.util.GregorianCalendar;

/**
 * Clase que contiene nombre del paciente, fecha de la facturacion, la cantidad
 * de consultas que hizo el paciente y el subtotal Esta clase la contendra en el
 * estado un medico
 */
public class Reporte implements Comparable {
    private String nombrePaciente;
    private GregorianCalendar fecha;
    private int cantConsultas;
    private double subtotal;

    public Reporte(GregorianCalendar fecha, String nombrePaciente, int cantConsultas, double subtotal) {
        this.fecha = fecha;
        this.nombrePaciente = nombrePaciente;
        this.cantConsultas = cantConsultas;
        this.subtotal = subtotal;
    }

    /**
     * Retorna la fecha de la facturacion
     * 
     * @return GregorianCalendar
     */
    public GregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Retorna el nombre del paciente
     * 
     * @return String
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * Retorna la cantidad de consultas
     * 
     * @return int
     */
    public int getCantConsultas() {
        return cantConsultas;
    }

    /**
     * Retorna el subtotal cobrado por el medico en este paciente
     * 
     * @return double
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Se basa en las fechas de los reportes Pre: El parametro debe ser de tipo
     * Reporte Post: retorna un valor negativo si this es menor que el parametro
     * retorna cero si son iguales retorna positivo si this es mayor que el
     * parametro
     * 
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Object o) {
        Reporte aux = (Reporte) o;
        return this.fecha.compareTo(aux.fecha);
    }

    /**
     * Retorna una cadena que brinda informacion del estado del objeto
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "\nnombrePaciente=" + nombrePaciente + "" + "\ncantConsultas= " + cantConsultas + "\nfecha="
                + fecha.getTime() + "\nsubtotal= " + subtotal;
    }

}
