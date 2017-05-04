/**
 * 
 */
package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.exception.MyException;

/**
 * Define los métodos para la capa de acceso a datos de las apuestas
 * @author Andrés Ceballos Sánchez - andres.ceballoss@udea.edu.co
 * @version 1.0 
 */

public interface ApuestaDAO {

	/**
	 * Registrar una apuesta en la base de datos
	 * @param apuesta - DTO con los datos de la apuesta a registrar
	 * @throws MyException
	 */
	public void registrar(Apuesta apuesta) throws MyException;
	
	/**
	 * Entrega la lista de apuestas de un periodo de simulacion dado
	 * @return Lista de apuestas
	 * @param periodoSimulacion - codigo del periodo de simulacion a consultar
	 * @throws MyException cuando hay un error en la consulta
	 */
	public List<Apuesta> consultar(Long periodoSimulacion) throws MyException;
}
