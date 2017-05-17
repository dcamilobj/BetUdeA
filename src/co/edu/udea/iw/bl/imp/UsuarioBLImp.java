/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UsuarioBL;
import co.edu.udea.iw.dao.SimulacionDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dao.imp.UsuarioDAOImp;
import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.encode.Cipher;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase para implementar todos los métodos de la lógica de negocio correspondiente al usuario
 * @author Duban Camilo Bedoya Jiménez
 * @version 1.0
 */
@Transactional
public class UsuarioBLImp implements UsuarioBL{	
	
	/*Atributos*/
	private UsuarioDAO usuarioDAO;
	private SimulacionDAO simulacionDAO;	
	
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
	 * @return the simulacionDAO
	 */
	public SimulacionDAO getSimulacionDAO() {
		return simulacionDAO;
	}


	/**
	 * @param simulacionDAO the simulacionDAO to set
	 */
	public void setSimulacionDAO(SimulacionDAO simulacionDAO) {
		this.simulacionDAO = simulacionDAO;
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
	public void registrar(String nombreUsuario, String tipoDocumento,
			String numeroDocumento, String nombres, String apellidos, 
			Date fechaNacimiento, String email, String password) throws MyException
	{
		Cipher cipher = new Cipher();
		
		/**
		 * Condiciones para verificar que los atributos de un usuario no sean nulos
		 * o vacíos
		 */
		if(nombreUsuario == null || nombreUsuario.isEmpty())
		{
			throw new MyException("El nombre de usuario ingresado es vacío o nulo");
		}
		if(tipoDocumento == null || tipoDocumento.isEmpty())
		{
			throw new MyException("El tipo de documento ingresado es vacío o nulo");
		}
		if(numeroDocumento == null || numeroDocumento.isEmpty())
		{
			throw new MyException("El número de documento ingresado es vacío o nulo");
		}
		if(nombres == null || nombres.isEmpty())
		{
			throw new MyException("El nombre ingresado es vacío o nulo");
		}
		if(apellidos == null || apellidos.isEmpty())
		{
			throw new MyException("Los apellidos ingresados están vacíos o son nulos");
		}
		if(fechaNacimiento == null || fechaNacimiento.toString().isEmpty())
		{
			throw new MyException("La fecha de nacimiento ingresada es vacía o nula");
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
		if(numeroDocumento.length() < 5  || numeroDocumento.length() > 15)
		{
			throw new MyException("La cédula debe contener entre 5 y 15 caracteres");
		}
		if(password.length()< 5)
		{
			throw new MyException("La contraseña debe tener más de 5 caracteres");
		}
		if(nombreUsuario.length()<5 || nombreUsuario.length() > 10)
		{
			throw new MyException("El nombre de usuario debe contener entre 5 y 10 caracteres");
		}
		
		/*Validar que la cuenta no haya sido creada previamente*/
		Usuario comprobarUsuario = new Usuario();
		comprobarUsuario = usuarioDAO.obtener(nombreUsuario);
		if(comprobarUsuario != null)
		{
			throw new MyException("El nombre de usuario ingresado ya existe");
		}
		
		/*Validar el correo del usuario*/
		comprobarUsuario = usuarioDAO.obtenerPorEmail(email);
		if(comprobarUsuario != null)
		{
			throw new MyException("El correo electrónico ingresado ya existe");
		}
		
		/*Validar el número de documento del usuario*/
		comprobarUsuario = usuarioDAO.obtenerPorDocumento(numeroDocumento);
		if(comprobarUsuario != null)
		{
			throw new MyException("El número de documento ingresado ya existe");
		}
			
		/*Validar que el usuario sea mayor de edad*/
		Date fechaActual = new Date();
		Long diferenciaFechas = fechaActual.getTime() - fechaNacimiento.getTime();
		if(diferenciaFechas < 568156314877L)
		{
			throw new MyException("El sistema sólo permite usuarios mayores de edad.");
		}		
		/*Si se cumplen todas las validaciones se registra el usuario*/
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setTipoDocumento(tipoDocumento);
		usuario.setNumeroDocumento(numeroDocumento);
		usuario.setNombres(nombres);
		usuario.setApellidos(apellidos);
		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setEmail(email);
		usuario.setPassword(cipher.encrypt(password));		
		usuarioDAO.registrar(usuario);
		
		Simulacion simulacion = new Simulacion();
		simulacion.setUsuario(usuario);
		simulacion.setSaldo(SimulacionBLImp.SALDO_INICIAL);
		simulacion.setFechaInicio(new Date());
		simulacion.setFechaFin(null);
		
		simulacionDAO.registrarPeriodo(simulacion);
	}
	
	/**
	 * Método para validar el correo y la contraseña que fueron ingresados.
	 * La contraseña ingresada se codificó por medio del método SHA-1,
	 * para luego compararse con la que se encuentra en la base de datos. 
	 * @param email
	 * @param password
	 * @throws MyException
	 */
	public void autenticar(String nombreUsuario, String password) throws MyException
	{
		System.out.println(nombreUsuario);
		Cipher cipher = new Cipher();
		if(nombreUsuario == null || nombreUsuario.isEmpty())
		{
			throw new MyException("Se debe ingresar un nombre de usuario");
		}
		if(password == null || password.isEmpty())
		{
			throw new MyException("Se debe ingresar una contraseña");
		}
		
		/*Validar si el usuario está registrado en el sistema*/
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.obtener(nombreUsuario);
		if(usuario == null)
		{
			throw new MyException("El nombre de usuario o la contraseña "
					+ "ingresada no se encuentra en el sistema");
		}
		if(!usuario.getPassword().equals(cipher.encrypt(password)))
		{
			throw new MyException("El nombre de usuario o la contraseña "
					+ "ingresada no se encuentra en el sistema");		
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
		
		/*Validar formato del email*/ 
		
	    String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    Pattern r = Pattern.compile(pattern);  	    // Crear un objeto 'Pattern'
	    Matcher matcher = r.matcher(newEmail);
	    if(matcher.matches()==false)
	    {
	    	throw new MyException("La nuevo correo electrónico no es válido");
	    }
		
		/*Validar si el usuario está registrado en el sistema*/
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.obtenerPorEmail(currentEmail);
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
		usuario = usuarioDAO.obtenerPorEmail(currentEmail);
		if(usuario == null)
		{
			throw new MyException("Correo inválido");
		}
		if(!usuario.getPassword().equals(cipher.encrypt(currentPassword)))
		{
			throw new MyException("Contraseña incorrecta.");
		}
		usuario.setPassword(cipher.encrypt(newPassword));
	}
}
