/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	/**
	 * 
	 * 
	 * @param nombreUsuario
	 * @throws RemoteException
	 */
	public void registrarPeriodo(@QueryParam("nombreUsuario")String nombreUsuario) throws RemoteException{
		
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("1")
	/**
	 * 
	 * @param nombreUsuario
	 * @return List<Simulacion>
	 * @throws RemoteException
	 */
	public List<Simulacion> obtenerPeriodos(@QueryParam("nombreUsuario")String nombreUsuario) throws RemoteException {
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("2")
	/**
	 * 
	 * @param periodoSimulacionId
	 * @return Simulacion
	 * @throws RemoteException
	 */
	public Simulacion obtenerPeriodo(@QueryParam("periodoSimulacionId")Long periodoSimulacionId) throws RemoteException{
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("3")
	/**
	 * 
	 * @param nombreUsuario
	 * @return Simulacion
	 * @throws RemoteException
	 */
	public Simulacion obtenerPeriodoActivo(@QueryParam("nombreUsuario")String nombreUsuario) throws RemoteException{
		return null;
	}
}
