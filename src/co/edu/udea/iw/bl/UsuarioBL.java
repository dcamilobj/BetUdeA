/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.Date;

import co.edu.udea.iw.exception.MyException;

/**
 * @author Duban Camilo Bedoya Jiménez
 * @version 1.0
 */
public interface UsuarioBL {

	public void registrar(String nombreUsuario, String tipoDocumento,
			String numeroDocumento, String nombres, String apellidos, 
			Date fechaNacimiento, String email, String password) throws MyException;
	
	public void autenticar(String nombreUsuario, String password) throws MyException;
	
	public void editarEmail(String currentEmail, String currentPassword,
			String newEmail) throws MyException;
	
	public void editarPassword(String currentEmail, String currentPassword,
			String newPassword) throws MyException;
}
