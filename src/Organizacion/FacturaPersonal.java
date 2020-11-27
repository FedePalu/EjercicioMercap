package Organizacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Exceptions.FacturacionInvalidaException;

public class FacturaPersonal {
	Calendar fecha = Calendar.getInstance();
	List<Llamada> llamadasFinalizadas = new ArrayList<>();
	Llamada llamadaActual;
	double montoBasico;
	
	public FacturaPersonal(int montoBasico) {
		this.montoBasico = montoBasico;
	}
	
	// Para hallar la factura se debe especificar el mes
	public double facturar(int mes) {
		if(!esValidoElMes(mes)) throw new FacturacionInvalidaException("El mes indicado está fuera del rango (0-11)");
		double monto = montoBasico + llamadasFinalizadas.stream().filter(llamada -> llamada.mesInicio() == mes).mapToDouble(llamada -> llamada.facturarLlamada()).sum();
		return monto;	
	}
	
	public boolean esValidoElMes(int mes) {
		return 0 <= mes && mes < 12;
	}
	
	public void llamar() {
		llamadaActual = new Llamada(fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_WEEK),fecha.get(Calendar.HOUR),fecha.get(Calendar.MINUTE));
	}
	
	public void cortar(Llamada llamada) {
		llamada.finalizar(fecha.get(Calendar.HOUR),fecha.get(Calendar.MINUTE));
		llamadasFinalizadas.add(llamada);
	}
	
}
