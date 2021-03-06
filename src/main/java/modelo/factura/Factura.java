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

	/**
	 * Constructor de la factura del paciente. A la factura actual se le asocia un paciente.
	 * @param paciente
	 * @param fecha
	 * @throws PacienteInvalidoException
	 */
	public Factura(Paciente paciente, GregorianCalendar fecha) throws PacienteInvalidoException {
		numFacturaMax++;
		numFactura = numFacturaMax;
		this.fecha = fecha;

		if (paciente != null)
			this.paciente = paciente;
		else
			throw new PacienteInvalidoException("Se trato de crear una factura con un paciente null");
	}

	/**
	 * Genera la factura con los datos de Prestacion, Valor, Cantidad, Subtotal
	 * @return Retorna un StringBuilder con la factura del mismo.
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

		sb.append(String.format("%25s \t| %20s \t| %10s \t| %10s \n", "Prestacion", "Valor", "Cantidad", "Subtotal"));

		for (final Object[] entrada : datos) {
			sb.append(String.format("%25s \t| $%15.2f \t| %10d \t| $%10.2f \n", entrada));
		}

		sb.append(String.format("\nTotal: $%8.2f\n", costoTotal));


		return sb;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}
}
