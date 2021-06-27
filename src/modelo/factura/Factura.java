package modelo.factura;

import java.util.Enumeration;
import java.util.GregorianCalendar;

import modelo.exceptions.DiasInvalidosException;
import modelo.exceptions.PacienteInvalidoException;
import modelo.lugares.Habitacion;
import modelo.usuarios.Medico;
import modelo.usuarios.Paciente;

import javax.swing.table.TableModel;
import javax.swing.table.TableStringConverter;

/**
 * Esta clase brinda informacion sobre el transcurso del paciente en la Clinica
 * 
 */

public class Factura {
	private static int numFacturaMax = 0;
	private int numFactura;
	private Paciente paciente;
	private GregorianCalendar fecha;

	private final float valorAgregadoConsulta = 0.2f;

	public Factura(Paciente paciente, GregorianCalendar fecha) throws PacienteInvalidoException {
		numFacturaMax++;
		numFactura = numFacturaMax;
		this.fecha = fecha;

		if (paciente != null)
			this.paciente = paciente;
		else
			throw new PacienteInvalidoException("Se trato de crear una modelo.factura con un paciente null");
	}

	/**
	 * Imprime la modelo.factura con los datos de Prestacion, Valor, Cantidad, Subtotal
	 * Post: Imprime en formato de tabla la informacion
	 * @return
	 */
	public StringBuilder ImprimeFactura() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nFactura numero: " + numFactura + "\n");

		int contadorDatos = 0;

		float costoTotal = 0;

		var consultas = paciente.getConsultas();
		var internaciones = paciente.getInternaciones();

		Object[][] datos = new Object[consultas.size() + internaciones.size()][4];

		Enumeration<Medico> enumMedicos = consultas.keys();
		while (enumMedicos.hasMoreElements()) {
			Medico medActual = enumMedicos.nextElement();
			datos[contadorDatos][0] = medActual.getNombre();
			datos[contadorDatos][1] = medActual.getHonorario() * valorAgregadoConsulta;
			datos[contadorDatos][2] = consultas.get(medActual);
			datos[contadorDatos][3] = medActual.getHonorario() * valorAgregadoConsulta * consultas.get(medActual);
			costoTotal += medActual.getHonorario() * valorAgregadoConsulta * consultas.get(medActual);

			contadorDatos++;
		}

		Enumeration<Habitacion> enumHabitaciones = internaciones.keys();
		while (enumHabitaciones.hasMoreElements()) {
			Habitacion habActual = enumHabitaciones.nextElement();
			datos[contadorDatos][0] = habActual.IDTipoHabitacion();
			datos[contadorDatos][1] = habActual.getCostoAsignacion();
			datos[contadorDatos][2] = internaciones.get(habActual);
			try {
				datos[contadorDatos][3] = habActual.calculaArancel(internaciones.get(habActual));
				costoTotal += habActual.calculaArancel(internaciones.get(habActual));
			} catch (DiasInvalidosException e) {
				e.fillInStackTrace();
			}

			contadorDatos++;
		}

		sb.append(String.format("%25s | %25s | %8s | %7s\n", "Prestacion", "Valor", "Cantidad", "Subtotal"));

		for (final Object[] entrada : datos) {
			sb.append(String.format("%25s | $%15.2f | %8d | $%7.2f\n", entrada));
		}

		sb.append(String.format("\nTotal: $%8.2f\n", costoTotal));


		return sb;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}
}
