/**
 * 
 */
package co.edu.udea.iw.bl;

import co.edu.udea.iw.exception.MyException;

/**
 * @author user
 *
 */
public interface UsuarioBL {

	public void registrar(String cedula, String nombre, String apellidos, 
			String email, String password) throws MyException;
	
	public void autenticar(String email, String password) throws MyException;
	
	public void editarEmail(String currentEmail, String currentPassword,
			String newEmail) throws MyException;
	
	public void editarPassword(String currentEmail, String currentPassword,
			String newPassword) throws MyException;
}
