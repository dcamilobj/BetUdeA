package co.edu.udea.iw.bl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.exception.MyException;



/**
 * Clase para implementar todos los metodos de la logica de negocio correspondiente a una simulacion 
 * @author Sergio Llanos
 * @version 1.0
 */

@Transactional
public interface SimulacionBL {

	
	/**
	 * Metodo para registrar un periodo en la base de datos
	 * @param usuario_id
	 * @param saldo
	 * @param fecha_inicio
	 * @param fecha_fin
	 * @throws MyException
	 */
	public void registrarPeriodo(String nombreUsuario) throws MyException;
	
	/**
	 * Metodo para consultar la lista de periodos dado el nombre de usuario
	 * @param nombreUsuario
	 * @return Lista periodos de simulacion
	 * @throws MyException
	 */
	public List<Simulacion> obtenerPeriodos (String nombreUsuario) throws MyException; 
	
	/**
	 * Metodo para consultar un periodo de simulacion dado el nombre de usuario
	 * @param periodoSimulacionId
	 * @return periodo de simulacion
	 * @throws MyException
	 */
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws MyException;
	
	/**
	 * Metodo para consultar el periodo de simulacion activo de un usuario
	 * @param nombreUsuario
	 * @return
	 * @throws MyException
	 */
	public Simulacion obtenerPeriodoActivo(String nombreUsuario) throws MyException;
}
