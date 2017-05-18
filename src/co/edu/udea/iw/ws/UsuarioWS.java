/**
 * 
 */
package co.edu.udea.iw.ws;

import static org.hamcrest.CoreMatchers.theInstance;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UsuarioBL;
import co.edu.udea.iw.bl.imp.UsuarioBLImp;
import co.edu.udea.iw.encode.Cipher;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Duban Camilo Bedoya Jiménez(dcamilo.bedoya@udea.edu.co)
 * @version 1.0
 */
@Path("Usuario")
@Component
public class UsuarioWS {
	
	@Autowired
	private UsuarioBL usuarioBL;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/Usuario
	 * ?nombreUsuario=Camilo&tipoDocumento=CC&numeroDocumento=qwdasd&nombres=Camilo&apellidos=Bedoya&fechaNacimiento=1996-04-04&email=loca@gmail.com&password=cualquiercosa
	 * @param nombreUsuario
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param nombres
	 * @param apellidos
	 * @param fechaNacimiento
	 * @param email
	 * @param password
	 * @throws RemoteException
	 */
	public String registrarUsuario(@QueryParam("nombreUsuario")String nombreUsuario,
			@QueryParam("tipoDocumento")String tipoDocumento,
			@QueryParam("numeroDocumento")String numeroDocumento,
			@QueryParam("nombres")String nombres, 
			@QueryParam("apellidos")String apellidos, 
			@QueryParam("fechaNacimiento")String fechaNacimiento,
			@QueryParam("email")String email,
			@QueryParam("password")String password) throws RemoteException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaNacimientoD = null;
		try{
			fechaNacimientoD  = sdf.parse(fechaNacimiento);
			usuarioBL.registrar(nombreUsuario, tipoDocumento, numeroDocumento,
					nombres, apellidos, fechaNacimientoD, email, password);
			
		}catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para registrar usuario");
		} catch (ParseException e) {
			throw new RemoteException("Error en la fecha de nacimiento ingresada");
		}
		
		return "Usuario registrado exitosamente.";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("1")
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/Usuario/1?nombreUsuario=Camilo&password=cualquiercosa
	 * @param nombreUsuario
	 * @param password
	 * @throws RemoteException
	 */
	public String autenticar(@QueryParam("nombreUsuario")String nombreUsuario,
			@QueryParam("password")String password) throws RemoteException
	{
		try{
			System.out.println("\n\n" +nombreUsuario +"\n\n");
			usuarioBL.autenticar(nombreUsuario, password);
			
		}catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para autenticar usuario", e);
		}
		
		return "Usuario autenticado exitosamente.";
	}
	
	@POST
	@Path("2")
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/Usuario/
	 * 2?currentEmail=sdf@gmail.com&currentPassword=cualquiercosa&newEmail=nuevo@gmail.com
	 * @param currentEmail
	 * @param currentPassword
	 * @param newEmail
	 * @throws RemoteException
	 */
	public String editarEmail(@QueryParam("currentEmail")String currentEmail,
			@QueryParam("currentPassword")String currentPassword,
			@QueryParam("newEmail")String newEmail) throws RemoteException
	{
		try{
			usuarioBL.editarEmail(currentEmail, currentPassword, newEmail);
		}catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para editar el correo de un usuario", e);
		}
		
		return "Correo electrónico modificado exitosamente.";
	}
	
	@POST
	@Path("3")
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/Usuario/
	 * 3?currentEmail=nuevo@gmail.com&currentPassword=cualquiercosa&newPassword=otracosa
	 * @param currentEmail
	 * @param currentPassword
	 * @param newPassword
	 * @throws RemoteException
	 */
public String editarPassword(@QueryParam("currentEmail")String currentEmail, 
		@QueryParam("currentPassword")String currentPassword,
		@QueryParam("newPassword")String newPassword) throws RemoteException
	{
		try{
			usuarioBL.editarPassword(currentEmail, currentPassword, newPassword);
		}
		catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para editar la contraseña de un usuario", e);
		}
		
		return "Contraseña modificada exitosamente.";
	} 
}


