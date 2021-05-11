package factura;

import dnl.utils.text.table.TextTable;
import usuarios.Paciente;

public class Factura 
{
	private static int numFacturaMax = 0;
	private int numFactura;
	private Paciente paciente;
	
	private final float valorAgregadoConsulta = 0.2f;
	
	private int contadorFilaFactura;
	
	public Factura(Paciente paciente)
	{
		numFacturaMax++;
		numFactura = numFacturaMax;
		this.paciente = paciente;
	}

	@Override
	public String toString() 
	{
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
		
		
		
		consultas.forEach((medico, cant) -> 
		{
			datos[contadorDatos][0] = medico.getNombre();
			datos[contadorDatos][1] = medico.getHonorario() * valorAgregadoConsulta;
			datos[contadorDatos][2] = cant;
			datos[contadorDatos][3] = medico.getHonorario() * valorAgregadoConsulta * cant;
			
			contadorDatos = contadorDatos + 1;
		});
		
		
		TextTable tablaDatos = new TextTable(nombresColumnas, datos);
		
		String salida = tablaDatos.toString();
		
		
		salida.concat("\n" + "Total: " + costoTotal);
		
		return salida;
	}
	
	
	
	
}
