/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.Date;
import java.util.List;

import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.exception.MyException;

/**
 * Define los m�todos para la logica de negocio de las apuestas
 * @author Andres Ceballos Sanchez - andres.ceballoss@udea.edu.co
 * @version 1.0
 */
public interface ApuestaBL {
	

	/**
	 * Metodo para registrar una apuesta de un usuario
	 * @param evento
	 * @param fechaEvento
	 * @param valorApostado
	 * @param cuota
	 * @param opcionSeleccionada
	 * @param usuario
	 * @throws MyException
	 */
	public void registrar(String evento, Date fechaEvento, Double valorApostado,
			Double cuota, String opcionSeleccionada, String usuario) throws MyException;
	
	/**
	 * Metodo para consultar las apuestas dado un periodo de simulacion
	 * @param periodoSimulacion
	 * @return Lista de apuestas
	 * @throws MyException
	 */
	public List<Apuesta> consultar(Long periodoSimulacion) throws MyException;	
	
}
