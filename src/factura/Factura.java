package factura;

import java.util.Enumeration;
import java.util.GregorianCalendar;

import exceptions.DiasInvalidosException;
import exceptions.PacienteInvalidoException;
import lugares.Habitacion;
import usuarios.IMedico;
import usuarios.Paciente;

/**
 * Esta clase brinda informacion sobre el transcurso del paciente en la clinica
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
			throw new PacienteInvalidoException("Se trato de crear una factura con un paciente null");
	}

	/**
	 * Imprime la factura con los datos de Prestacion, Valor, Cantidad, Subtotal
	 * Post: Imprime en formato de tabla la informacion
	 */
	public void ImprimeFactura() {
		System.out.print("\nFactura numero: " + numFactura + "\n");

		int contadorDatos = 0;

		float costoTotal = 0;

		var consultas = paciente.getConsultas();
		var internaciones = paciente.getInternaciones();

		Object[][] datos = new Object[consultas.size() + internaciones.size()][4];

		Enumeration<IMedico> enumMedicos = consultas.keys();
		while (enumMedicos.hasMoreElements()) {
			IMedico medActual = enumMedicos.nextElement();
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

		System.out.format("%25s | %11s | %8s | %7s%n", "Prestacion", "Valor", "Cantidad", "Subtotal");

		for (final Object[] entrada : datos) {
			System.out.format("%25s | $%10.2f | %8d | $%7.2f%n", entrada);
		}

		System.out.format("\nTotal: $%8.2f%n", costoTotal);

	}

	public GregorianCalendar getFecha() {
		return fecha;
	}
}
