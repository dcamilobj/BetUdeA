/**
 * 
 */
package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Simulacion;

import co.edu.udea.iw.exception.MyException;

/**
 * Esta interfaz define las operaciones que se pueden hacer sobre la tabla periodo de simulacion.
 * @author Sergio
 * @version 1.0
 */
public interface SimulacionDAO {

	/**
	 * @param simulacion - DTO con los datos de la simulacion a registrar
	 * @throws MyException
	 */
	
	public void ingresarPeriodo(Simulacion simulacion) throws MyException;
	
	/**
	 * @param usuario_id - Identificacion del usuario al cual se le van a consultar los periodos
	 * @throws MyException
	 */
	
	public  List<Simulacion>  consultarPeriodos (String usuario_id) throws MyException;
	
	/**
	 * @param periodoSimulacionId - Identificacion del periodo al cual vamos a obtener
	 * @throws MyException
	 */
	
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws MyException;
	
	/**
	 * @param usuario_id - Identificacion del usuario al cual se le va a consultar un periodo
	 * @throws MyException
	 */
	
	public Simulacion periodoActivo(String usuario_id) throws MyException;
	
	
}
