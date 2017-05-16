/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.iw.bl.imp.UsuarioBLImp;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Duban Camilo Bedoya Jiménez(dcamilo.bedoya@udea.edu.co)
 * @version 1.0
 */
@Path("Usuario")
public class UsuarioWS {
	
	@Autowired
	private UsuarioBLImp usuarioBLImp;

	@POST
	@Produces(MediaType.TEXT_HTML)
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
			throw new RemoteException("Error en el servicio para registrar el usuario", e);
		}
	}
}

/*
 * 	public void guardar(@QueryParam("cedula")String cedula, 
			@QueryParam("nombres")String nombres,
			@QueryParam("apellidos")String apellidos,
			@QueryParam("email")String email, 
			@QueryParam("usuarioCrea")String usuarioCrea) throws RemoteException 
	{
		try
		{
		  clienteBL.guardar(cedula, nombres, apellidos, email, usuarioCrea);
		}catch(MyException e)
		{
			throw new RemoteException("Error creando el usuario", e);
		}
	}
	*/
