/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.UsuarioBL;
import co.edu.udea.iw.exception.MyException;

/**
 * Servicios web para entidad Usuario
 * @author Duban Camilo Bedoya Jiménez(dcamilo.bedoya@udea.edu.co)
 * @version 1.0
 */
@Path("usuarios")
@Component
public class UsuarioWS {

	@Autowired
	private UsuarioBL usuarioBL;

	/**
	 * Servicio web para registrar un usuario
	 * http://localhost:8080/BetUdeA/usuarios?nombreUsuario=usertest&tipoDocumento=CC&numeroDocumento=123456789&nombres=testname
	 * &apellidos=testlastname&fechaNacimiento=1996-04-04&email=test@gmail.com&password=passwordtest
	 * 
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
	@POST
	public void registrarUsuario(@QueryParam("nombreUsuario") String nombreUsuario,
			@QueryParam("tipoDocumento") String tipoDocumento, @QueryParam("numeroDocumento") String numeroDocumento,
			@QueryParam("nombres") String nombres, @QueryParam("apellidos") String apellidos,
			@QueryParam("fechaNacimiento") String fechaNacimiento, @QueryParam("email") String email,
			@QueryParam("password") String password) throws RemoteException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaNacimientoD = null;
		try {
			fechaNacimientoD = sdf.parse(fechaNacimiento);
			usuarioBL.registrar(nombreUsuario, tipoDocumento, numeroDocumento, nombres, apellidos, fechaNacimientoD,
					email, password);

		} catch (MyException e) {
			throw new RemoteException("Error en el servicio para registrar usuario");
		} catch (ParseException e) {
			throw new RemoteException("Error en la fecha de nacimiento ingresada");
		} catch (NullPointerException e) {
			throw new RemoteException("Se debe ingresar una fecha de nacimiento");
		}
	}

	/**
	 * Servicio web para autenticar los usuarios
	 * http://localhost:8080/BetUdeA/usuarios/autenticar?nombreUsuario=usertest&password=passwordtest
	 * 
	 * @param nombreUsuario
	 * @param password
	 * @throws RemoteException
	 */
	@POST
	@Path("autenticar")
	public void autenticar(@QueryParam("nombreUsuario") String nombreUsuario, @QueryParam("password") String password)
			throws RemoteException {
		try {
			System.out.println("\n\n" + nombreUsuario + "\n\n");
			usuarioBL.autenticar(nombreUsuario, password);

		} catch (MyException e) {
			throw new RemoteException("Error en el servicio para autenticar usuario", e);
		}

	}

	/**
	 * Servicio web para editar el email de un usuario
	 * http://localhost:8080/BetUdeA/usuarios/editaremail?
	 * currentEmail=test@gmail.com&currentPassword=passwordtest&newEmail=newtest@gmail.com
	 * 
	 * @param currentEmail
	 * @param currentPassword
	 * @param newEmail
	 * @throws RemoteException
	 */
	@POST
	@Path("editaremail")
	public void editarEmail(@QueryParam("currentEmail") String currentEmail,
			@QueryParam("currentPassword") String currentPassword, @QueryParam("newEmail") String newEmail)
			throws RemoteException {
		try {
			usuarioBL.editarEmail(currentEmail, currentPassword, newEmail);
		} catch (MyException e) {
			throw new RemoteException("Error en el servicio para editar el correo de un usuario", e);
		}

	}

	/**
	 * Servicio web para editar la contrase�a de un usuario
	 * http://localhost:8080/BetUdeA/usuarios/editarpassword?
	 * currentEmail=newtest@gmail.com&currentPassword=passwordtest&newPassword=newpasswordtest 
	 * @param currentEmail
	 * @param currentPassword
	 * @param newPassword
	 * @throws RemoteException
	 */
	@POST
	@Path("editarpassword")
	public void editarPassword(@QueryParam("currentEmail") String currentEmail,
			@QueryParam("currentPassword") String currentPassword, @QueryParam("newPassword") String newPassword)
			throws RemoteException {
		try {
			usuarioBL.editarPassword(currentEmail, currentPassword, newPassword);
		} catch (MyException e) {
			throw new RemoteException("Error en el servicio para editar la contraseña de un usuario", e);
		}

	}
}
