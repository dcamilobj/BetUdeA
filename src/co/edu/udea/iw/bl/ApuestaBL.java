/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.Date;
import java.util.List;

import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Andres Ceballos Sanchez - andres.ceballoss@udea.edu.co
 * @version 1.0
 */
public interface ApuestaBL {
	

	//fecha es Date o es String en este metodo
	public void registrar(String evento, Date fechaEvento, Double valorApostado,
			Double cuota, String opcionSeleccionada, String usuario) throws MyException;
	
	public List<Apuesta> consultar(Long periodoSimulacion) throws MyException;	
	
}
