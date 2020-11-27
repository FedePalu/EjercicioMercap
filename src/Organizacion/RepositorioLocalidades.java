package Organizacion;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLocalidades {

	public static RepositorioLocalidades instance = new RepositorioLocalidades();

	List<String> ciudades = new ArrayList<String>();
	
	public String getCiudad() {
		//Retorna la ciudad que se está realizando la llamadacomo string
		return null;
	}
	
	public boolean esInternacional(String ciudad) {
		//Cheque si la ciudad es internacional
		return true;
	}

	public boolean esLocal(String ciudad) {
		//Chequea si la ciudad es local
		return false;
	}
	
	
	
}
