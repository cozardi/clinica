package factura;

import java.util.GregorianCalendar;

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

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public int getCantConsultas() {
        return cantConsultas;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public int compareTo(Object o) {
        Reporte aux = (Reporte) o;
        return this.fecha.compareTo(aux.fecha);
    }

    @Override
    public String toString() {
        return "nombrePaciente=" + nombrePaciente + " " + " cantConsultas= " + cantConsultas + " fecha="
                + fecha.getTime() + " subtotal= " + subtotal + "]";
    }

}
