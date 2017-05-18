/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
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

@Path("Simulacion")
@Component
public class SimulacionWS {

	@Autowired
	private SimulacionBL simulacionBL;
	
	@POST
	@Produces(MediaType.TEXT_HTML)	
	/**
	 * 
	 * http://localhost:8080/BetUdeA/BetUdeA/Simulacion?nombreUsuario=elver
	 * @param nombreUsuario
	 * @throws RemoteException
	 */
	public void registrarPeriodo(@QueryParam("nombreUsuario")String nombreUsuario) throws RemoteException{
		try{
			simulacionBL.registrarPeriodo(nombreUsuario);

		}catch(MyException e)
		{
			throw new RemoteException("Error en el servicio para registrar un periodo de simulaci�n", e);
		}
	
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("1")
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/Simulacion/1?nombreUsuario=elver
	 * @param nombreUsuario
	 * @return List<Simulacion>
	 * @throws RemoteException
	 */
	public List<Simulacion> obtenerPeriodos(@QueryParam("nombreUsuario")String nombreUsuario) throws RemoteException {
		List<Simulacion> respuesta = null; 
		try{
			respuesta=simulacionBL.obtenerPeriodos(nombreUsuario);
		}
		catch(MyException e){
				throw new RemoteException("Error obteniendo los periodos de simulacion de ese usuario",e);
		}
		return respuesta;
					}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("2")
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/Simulacion/2?periodoSimulacionId=1
	 * @param periodoSimulacionId
	 * @return Simulacion
	 * @throws RemoteException
	 */
	public Simulacion obtenerPeriodo(@QueryParam("periodoSimulacionId")Long periodoSimulacionId) throws RemoteException{
		Simulacion respuesta=null;
		try{
			respuesta=simulacionBL.obtenerPeriodo(periodoSimulacionId);
		}
		catch(MyException e){
				throw new RemoteException("Error obteniendo el periodo de simulaci�n con ese Id",e);
		}
		return respuesta;
	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	@Path("3")
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/Simulacion/3?nombreUsuario=elver
	 * @param nombreUsuarior
	 * @return Simulacion
	 * @throws RemoteException
	 */
	public Simulacion obtenerPeriodoActivo(@QueryParam("nombreUsuario")String nombreUsuario) throws RemoteException{
		Simulacion respuesta=null;
		try{
			respuesta=simulacionBL.obtenerPeriodoActivo(nombreUsuario);
		}
		catch(MyException e){
				throw new RemoteException("Error obteniendo el periodo de simulaci�n activo",e);
		}
		return respuesta;
	}
}
