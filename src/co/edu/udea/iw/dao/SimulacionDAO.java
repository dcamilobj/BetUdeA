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
	 * Registra un periodo de simulacion en la base de datos
	 * @param simulacion - DTO con los datos de la simulacion a registrar
	 * @throws MyException
	 */
	
	public void registrarPeriodo(Simulacion simulacion) throws MyException;
	
	/**
	 * Entrega la lista de periodos de simulacion de un usuario dado
	 * @param nombreUsuario - nombre de usuario a consultar 
	 * @throws MyException
	 */
	
	public List<Simulacion> obtenerPeriodos (String nombreUsuario) throws MyException;
	
	/**
	 * Consulta la informacion de un periodo de simulacion dado su id
	 * @param periodoSimulacionId - id del periodo a consultar
	 * @throws MyException cuando se presenta algun error consultando
	 */
	
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws MyException;
	
	/**
	 * Retorna el periodo de simulacion activo dado un usuario
	 * @param nombreUsuario - nombre de usuario a consultar
	 * @throws MyException
	 */
	
	public Simulacion obtenerPeriodoActivo(String nombreUsuario) throws MyException;
	
	
}
