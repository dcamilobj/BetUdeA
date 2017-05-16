/**
 * 
 */
package co.edu.udea.iw.ws;

import static org.hamcrest.CoreMatchers.theInstance;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.imp.UsuarioBLImp;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Duban Camilo Bedoya Jiménez(dcamilo.bedoya@udea.edu.co)
 * @version 1.0
 */
@Path("Usuario")
@Component
public class UsuarioWS {
	
	@Autowired
	private UsuarioBLImp usuarioBLImp;

	@POST
	@Produces(MediaType.TEXT_HTML)
	/**
	 * ?nombreUsuario="Camilo"&tipoDocumento="CC"&numeroDocumento="qwdasd"&nombres="Camilo"&apellidos="Bedoya"&fechaNacimiento=1996-04-04&email="loca@gmail.com"&password="oeoeoeo"
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
	public void registrar(@QueryParam("nombreUsuario")String nombreUsuario,
			@QueryParam("tipoDocumento")String tipoDocumento,
			@QueryParam("numeroDocumento")String numeroDocumento,
			@QueryParam("nombres")String nombres, 
			@QueryParam("apellidos")String apellidos, 
			@QueryParam("fechaNacimiento")Date fechaNacimiento,
			@QueryParam("email")String email,
			@QueryParam("password")String password) throws RemoteException
	{
		try{
			usuarioBLImp.registrar(nombreUsuario, tipoDocumento, numeroDocumento,
					nombres, apellidos, fechaNacimiento, email, password);

			
		}catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para registrar	 usuario", e);
		}
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * 
	 * @param nombreUsuario
	 * @param password
	 * @throws RemoteException
	 */
	public void autenticar(@QueryParam("nombreUsuario")String nombreUsuario,
			@QueryParam("password")String password) throws RemoteException
	{
		try{
			usuarioBLImp.autenticar(nombreUsuario, password);
		}catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para autenticar usuario", e);
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * 
	 * @param currentEmail
	 * @param currentPassword
	 * @param newEmail
	 * @throws RemoteException
	 */
	public void editarEmail(@QueryParam("currentEmail")String currentEmail,
			@QueryParam("currentPassword")String currentPassword,
			@QueryParam("newEmail")String newEmail) throws RemoteException
	{
		try{
			usuarioBLImp.editarEmail(currentEmail, currentPassword, newEmail);
		}catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para editar el correo de un usuario", e);
		}
	}
	
	/**
	 * 
	 * @param currentEmail
	 * @param currentPassword
	 * @param newPassword
	 * @throws RemoteException
	 */
public void editarPassword(@QueryParam("currentEmail")String currentEmail, 
		@QueryParam("currentPassword")String currentPassword,
		@QueryParam("newPassword")String newPassword) throws RemoteException
	{
		try{
			usuarioBLImp.editarPassword(currentEmail, currentPassword, newPassword);
		}
		catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para editar la contraseña de un usuario", e);
		}
	}
}


