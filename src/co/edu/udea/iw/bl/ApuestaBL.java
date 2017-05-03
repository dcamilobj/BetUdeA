/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.List;

import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Andres Ceballos Sanchez - andres.ceballoss@udea.edu.co
 * @version 1.0
 */
public interface ApuestaBL {
	
	//fecha es Date o es String en este metodo
	public void registrar(String evento, String fechaEvento, Long valorApostado,
			Long cuota, String opcionSeleccionada, Integer usuario) throws MyException;
	
	public List<Apuesta> consultar(Integer periodoSimulacion) throws MyException;
	
	
}
