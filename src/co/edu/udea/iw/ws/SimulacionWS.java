/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.SimulacionBL;
import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Sergio
 * @version 1.0
 *
 */

@Path("simulacion")
@Component
public class SimulacionWS {

	@Autowired
	private SimulacionBL simulacionBL;
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public void registrarPeriodo(String nombreUsuario) throws RemoteException{
		
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("1")
	public List<Simulacion> obtenerPeriodos(String nombreUsuario) throws RemoteException {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("2")
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws RemoteException{
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("2")
	public Simulacion obtenerPeriodoActivo(String nombreUsuario) throws RemoteException{

	}
}
