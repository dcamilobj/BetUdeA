/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dao.imp.UsuarioDAOImp;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.encode.Cipher;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase para implementar todos los métodos de la lógica de negocio correspondiente al usuario
 * @author Duban Camilo Bedoya Jiménez
 * @version 1.0
 */
@Transactional
public class UsuarioBL {
	
	UsuarioDAO usuarioDAO;
	
	
	/**
	 * @return the usuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}


	/**
	 * @param usuarioDAO the usuarioDAO to set
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}


	/**
	 * Método para registrar un usuario en la base de datos,
	 * la contraseña se cifró por medio del método SHA-1.
	 * @param cedula
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param password
	 * @throws MyException
	 */
	public void registrar(String cedula, String nombre, String apellidos, 
			String email, String password) throws MyException
	{
		Cipher cipher = new Cipher();
		
		/**
		 * Condiciones para verificar que los atributos de un usuario no sean nulos
		 * o vacíos
		 */
		if(cedula == null || cedula.isEmpty())
		{
			throw new MyException("La cédula ingresada es vacía o nula");
		}
		if(nombre == null || nombre.isEmpty())
		{
			throw new MyException("El nombre ingresado es vacío o nulo");
		}
		if(apellidos == null || apellidos.isEmpty())
		{
			throw new MyException("Los apellidos ingresados están vacíos o son nulos");
		}
		if(email == null || email.isEmpty())
		{
			throw new MyException("El correo ingresado es vacío o nulo");
		}
		if(password  == null || password.isEmpty())
		{
			throw new MyException("La contraseña ingresada es vacía o nula");
		}
		
		/*Validar formato del email*/ 
		
	    String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    Pattern r = Pattern.compile(pattern);  	    // Crear un objeto 'Pattern'
	    Matcher matcher = r.matcher(email);
	    if(matcher.matches()==false)
	    {
	    	throw new MyException("La correo electrónico ingresado no es válido");
	    }
		
		/*Validar longitud de los campos*/
		if(cedula.length() < 5)
		{
			throw new MyException("La cédula debe tener más de 5 caracteres");
		}
		if(password.length()< 5)
		{
			throw new MyException("La contraseña debe tener más de 5 caracteres");
		}
		
		Usuario usuario = new Usuario();
		usuario.setCedula(cedula);
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setEmail(email);
		usuario.setPassword(cipher.encrypt(password));
		
		usuarioDAO.registrar(usuario);
	}
	
	/**
	 * Método para validar el correo y la contraseña que fueron ingresados.
	 * La contraseña ingresada se codificó por medio del método SHA-1,
	 * para luego compararse con la que se encuentra en la base de datos. 
	 * @param email
	 * @param password
	 * @throws MyException
	 */
	public void autenticar(String email, String password) throws MyException
	{
		Cipher cipher = new Cipher();
		if(email == null || email.isEmpty())
		{
			throw new MyException("Se debe ingresar un correo electrónico");
		}
		if(password == null || password.isEmpty())
		{
			throw new MyException("Se debe ingresar una contraseña");
		}
		
		/*Validar si el usuario está registrado en el sistema*/
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.obtener(email);
		if(usuario == null)
		{
			System.out.println("\n\n Entré al null papo \n\n");
			throw new MyException("El correo o la contraseña ingresada no se encuentra en"
					+ " el sistema");
		}
		System.out.println("\n\n\n" +usuario.getNombre()+ "\n\n\n");
		if(!usuario.getPassword().equals(cipher.encrypt(password)))
		{
			throw new MyException("El correo o la contraseña ingresada no se encuentra en"
					+ " el sistema");			
		}
	}
	
	/**
	 * Método que permite al usuario editar su correo electrónico.
	 * @param currentEmail
	 * @param currentPassword
	 * @param newEmail
	 * @throws MyException
	 */
	public void editarEmail(String currentEmail, String currentPassword,
			String newEmail) throws MyException
	{
		Cipher cipher = new Cipher();
		if(currentEmail == null || currentEmail.isEmpty())
		{
			throw new MyException("Se debe ingresar el correo actual");
		}
		if(currentPassword == null || currentPassword.isEmpty())
		{
			throw new MyException("Se debe ingresar la contraseña actual");
		}
		if(newEmail == null || newEmail.isEmpty())
		{
			throw new MyException("Se debe ingresar el nuevo correo");
		}
		
		/*Validar si el usuario está registrado en el sistema*/
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.obtener(currentEmail);
		if(usuario == null)
		{
			throw new MyException("Correo inválido");
		}
		if(!usuario.getPassword().equals(cipher.encrypt(currentPassword)))
		{
			throw new MyException("Contraseña incorrecta.");
		}
		usuario.setEmail(newEmail);
	}
	
	/**
	 * Método que permite al usuario editar su contraseña.
	 * @param currentEmail
	 * @param currentPassword
	 * @param newPassword
	 * @throws MyException
	 */
	public void editarPassword(String currentEmail, String currentPassword,
			String newPassword) throws MyException
	{
		Cipher cipher = new Cipher();
		if(currentEmail == null || currentEmail.isEmpty())
		{
			throw new MyException("Se debe ingresar el correo actual");
		}
		if(currentPassword == null || currentPassword.isEmpty())
		{
			throw new MyException("Se debe ingresar la contraseña actual");
		}
		if(newPassword == null || newPassword.isEmpty())
		{
			throw new MyException("Se debe ingresar la nueva contraseña");
		}
		
		/*Validar si el usuario está registrado en el sistema*/
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.obtener(currentEmail);
		if(usuario == null)
		{
			throw new MyException("Correo inválido");
		}
		if(!usuario.getPassword().equals(cipher.encrypt(currentPassword)))
		{
			throw new MyException("Contraseña incorrecta.");
		}
		usuario.setPassword(newPassword);
	}
}
