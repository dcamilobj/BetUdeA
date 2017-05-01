/**
 * 
 */
package co.edu.udea.iw.dao;

import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 *Esta interfaz define las operaciones que se pueden hacer sobre la tabla usuario en el sistema.
 *@author Duban Camilo Bedoya Jiménez
 *@version 1.0
 */
public interface UsuarioDAO {

	/**
	 * 
	 * @throws MyException
	 */
	public void registrar(Usuario usuario) throws MyException;
	
	/**
	 * 
	 * @throws MyException
	 */
	public Usuario obtener(String email) throws MyException;
	
	/**
	 * 
	 * @throws MyException
	 */
	public void editar(Usuario usuario) throws MyException;
}
