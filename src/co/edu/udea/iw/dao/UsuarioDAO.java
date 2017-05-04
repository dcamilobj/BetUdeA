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
	 * Definición de método para registrar un usuario en la base de datos.
	 * @param usuario
	 * @throws MyException
	 */
	public void registrar(Usuario usuario) throws MyException;
	
	/**
	 * Definición de método para obtener un usuario de la base de datos dado su
	 * nombre de usuario.
	 * @param nombre de usuario
	 * @return usuario con el nombre de usuario dado
	 * @throws MyException
	 */
	public Usuario obtener(String nombreUsuario) throws MyException;
	
	/**
	 * Definición de método para obtener un usuario de la base de datos dado su email.
	 * @param email
	 * @return usuario con el email dado
	 * @throws MyException
	 */
	public Usuario obtenerPorEmail(String email) throws MyException;
}
