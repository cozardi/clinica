package factura;

import java.util.Enumeration;

import dnl.utils.text.table.TextTable;
import exceptions.DiasInvalidosException;
import exceptions.PacienteInvalidoException;
import lugares.Habitacion;
import usuarios.IMedico;
import usuarios.Paciente;

public class Factura 
{
	private static int numFacturaMax = 0;
	private int numFactura;
	private Paciente paciente;
	
	private final float valorAgregadoConsulta = 0.2f;
	
	private int contadorFilaFactura;
	
	public Factura(Paciente paciente) throws PacienteInvalidoException
	{
		numFacturaMax++;
		numFactura = numFacturaMax;
		if (paciente != null)
			this.paciente = paciente;
		else
			throw new PacienteInvalidoException("Se trato de crear una factura con un paciente null");
	}

	public void ImprimeFactura() 
	{
		System.out.print("Factura numero: " + numFactura + "\n");
		
		String[] nombresColumnas = {
				"Prestacion",
				"Valor",
				"Cantidad",
				"Subtotal"};
		
		int contadorDatos = 0;
		
		
		
		int costoTotal = 0;
		
		var consultas = paciente.getConsultas();
		var internaciones = paciente.getInternaciones();
		
		Object[][] datos = new Object[consultas.size() + internaciones.size()][nombresColumnas.length];
		
		
		Enumeration<IMedico> enumMedicos = consultas.keys();
		while (enumMedicos.hasMoreElements())
		{
			IMedico medActual = enumMedicos.nextElement();
			datos[contadorDatos][0] = medActual.getNombre();
			datos[contadorDatos][1] = (int) (medActual.getHonorario() * valorAgregadoConsulta);
			datos[contadorDatos][2] = consultas.get(medActual);
			datos[contadorDatos][3] = (int) (medActual.getHonorario() * valorAgregadoConsulta * consultas.get(medActual));
			costoTotal += medActual.getHonorario() * valorAgregadoConsulta * consultas.get(medActual);
			
			contadorDatos++;
		}
		
		
		Enumeration<Habitacion> enumHabitaciones = internaciones.keys();
		while(enumHabitaciones.hasMoreElements())
		{
			Habitacion habActual = enumHabitaciones.nextElement();
			datos[contadorDatos][0] = habActual.IDTipoHabitacion();
			datos[contadorDatos][1] = (int) habActual.getCostoAsignacion();
			datos[contadorDatos][2] = internaciones.get(habActual);
			try {
				datos[contadorDatos][3] = (int) habActual.calculaArancel(internaciones.get(habActual));
				costoTotal += habActual.calculaArancel(internaciones.get(habActual));
			} catch (DiasInvalidosException e) {
				e.fillInStackTrace();
			}
			
			contadorDatos++;
		}
		
		TextTable tablaDatos = new TextTable(nombresColumnas, datos);
		
		
		tablaDatos.printTable();
		
		
		System.out.print("\n" + "Total: " + costoTotal);
		
	}
	
	
	
	
}
