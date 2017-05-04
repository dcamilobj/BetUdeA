/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.Date;

import co.edu.udea.iw.exception.MyException;

/**
 * Define los m�todos para la logica de negocio de los usuarios
 * @author Duban Camilo Bedoya Jiménez
 * @version 1.0
 */
public interface UsuarioBL {

	/**
	 * Metodo para registrar un usuario en la base de datos
	 * @param nombreUsuario
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param nombres
	 * @param apellidos
	 * @param fechaNacimiento
	 * @param email
	 * @param password
	 * @throws MyException
	 */
	public void registrar(String nombreUsuario, String tipoDocumento,
			String numeroDocumento, String nombres, String apellidos, 
			Date fechaNacimiento, String email, String password) throws MyException;
	
	/**
	 * Método para validar el correo y la contraseña que fueron ingresados.
	 * @param nombreUsuario
	 * @param password
	 * @throws MyException
	 */
	public void autenticar(String nombreUsuario, String password) throws MyException;
	
	/**
	 * Método que permite al usuario editar su correo electrónico.
	 * @param currentEmail
	 * @param currentPassword
	 * @param newEmail
	 * @throws MyException
	 */
	public void editarEmail(String currentEmail, String currentPassword,
			String newEmail) throws MyException;
	
	/**
	 * Método que permite al usuario editar su contraseña.
	 * @param currentEmail
	 * @param currentPassword
	 * @param newPassword
	 * @throws MyException
	 */
	public void editarPassword(String currentEmail, String currentPassword,
			String newPassword) throws MyException;
}
