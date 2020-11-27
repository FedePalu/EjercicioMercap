package Organizacion;

import Exceptions.FacturacionInvalidaException;


public class Llamada {
	int mesInicio;
	int horaInicio;
	int minutoInicio;
	int diaInicio;
	boolean estaFinalizada = false;
	
	int horaFinal;
	int minutoFinal;
	
	// No se tienen en cuenta las llamadas que su duracion pertenezca a 2 dias diferentes
	
	public Llamada(int mesInicio, int diaInicio, int horaInicio, int minutoInicio) {
		this.mesInicio = mesInicio;
		this.diaInicio = diaInicio;
		this.horaInicio = horaInicio;
		this.minutoInicio = minutoInicio;
	}
	
	public int mesInicio() {
		return mesInicio;
	}
	
	public double consumoLlamadasLocales() {
		if(esDiaHabil() && esHoraPico()) return this.minutosTotales() * 0.2;
		return this.minutosTotales() * 0.1;
	}

	public double consumoLlamadasNoLocales(String ciudad) {
		if(RepositorioLocalidades.instance.esInternacional(ciudad))
			//Criterio por cada pais
			return 1;
		//Criterio por cada nacion
		return 2;
	}
	
	private int minutosTotales() {
		return (horaFinal * 60 + minutoFinal) - (horaInicio * 60 + minutoInicio);
	}
	
	
	private boolean esHoraPico() {
		return 7 < horaInicio && horaInicio < 21;
	}
	
	private boolean esDiaHabil() {
		return 0 < diaInicio && diaInicio < 7;
	}

	
	public double facturarLlamada() {
		if(!estaFinalizada) throw new FacturacionInvalidaException("La llamada aún no ha finalizada");
		String ciudad = RepositorioLocalidades.instance.getCiudad();
		if(RepositorioLocalidades.instance.esLocal(ciudad)) return consumoLlamadasLocales();
		return consumoLlamadasNoLocales(ciudad);
	}

	public void finalizar(int horaFinal, int minutoFinal) {
		this.estaFinalizada = true;
		this.horaFinal = horaFinal;
		this.minutoFinal = minutoFinal;
	}

}
