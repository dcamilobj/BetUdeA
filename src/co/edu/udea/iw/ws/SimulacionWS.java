/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.SimulacionBL;
import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.exception.MyException;

/**
 * Servicios web para entidad periodo de simulación.
 * @author Sergio Llanos García
 * @version 1.0
 *
 */

@Path("periodos")
@Component
public class SimulacionWS {

	@Autowired
	private SimulacionBL simulacionBL;

	/**
	 * Servicio web para registrar un periodo de simulacion
	 * http://localhost:8080/BetUdeA/periodos?nombreUsuario=usertest
	 * 
	 * @param nombreUsuario
	 * @throws RemoteException
	 */
	@POST
	public void registrarPeriodo(@QueryParam("nombreUsuario") String nombreUsuario) throws RemoteException {
		try {
			simulacionBL.registrarPeriodo(nombreUsuario);

		} catch (MyException e) {
			throw new RemoteException("Error en el servicio para registrar un periodo de simulaci�n", e);
		}

	}

	/**
	 * Servicio web para consultar los periodos de simulacion de un usuario dado
	 * http://localhost:8080/BetUdeA/periodos?nombreUsuario=usertest
	 * 
	 * @param nombreUsuario
	 * @return List<Simulacion>
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Simulacion> consultarPeriodos(@QueryParam("nombreUsuario") String nombreUsuario)
			throws RemoteException {
		List<Simulacion> resultado = new ArrayList<>();
		List<Simulacion> periodos = null;
		Simulacion simulacionWS = null;
		try {
			periodos = simulacionBL.obtenerPeriodos(nombreUsuario);
			for (Simulacion simulacion : periodos) {
				simulacionWS = new Simulacion();
				simulacionWS.setId(simulacion.getId());
				simulacionWS.setFechaInicio(simulacion.getFechaInicio());
				simulacionWS.setFechaFin(simulacion.getFechaFin());
				simulacionWS.setSaldo(simulacion.getSaldo());
				resultado.add(simulacionWS);
			}
		} catch (MyException e) {
			throw new RemoteException("Error obteniendo los periodos de simulacion de ese usuario", e);
		}
		return resultado;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{periodoSimulacionId}")
	/**
	 * Servicio web para consultar un periodo de simulacion dado su id
	 * http://localhost:8080/BetUdeA/periodos/...
	 * @param periodoSimulacionId
	 * @return Simulacion
	 * @throws RemoteException
	 */
	public Simulacion consultarPeriodo(@PathParam("periodoSimulacionId") Long periodoSimulacionId)
			throws RemoteException {
		Simulacion periodo = null;
		Simulacion respuesta = new Simulacion();
		try {
			periodo = simulacionBL.obtenerPeriodo(periodoSimulacionId);
			respuesta.setId(periodo.getId());
			respuesta.setFechaInicio(periodo.getFechaInicio());
			respuesta.setFechaFin(periodo.getFechaFin());
			respuesta.setSaldo(periodo.getSaldo());
		} catch (MyException e) {
			throw new RemoteException("Error obteniendo el periodo de simulaci�n con ese Id", e);
		} catch(NullPointerException e) {
			throw new RemoteException("El periodo especificado no existe en el sistema");
		}
		return respuesta;

	}

	
	/**
	 * Servicio web para consultar el periodo de simulacion activo de un usuario
	 * http://localhost:8080/BetUdeA/periodos/periodoactivo?nombreUsuario=usertest	  
	 * @param nombreUsuarior
	 * @return Simulacion
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("periodoactivo")
	public Simulacion consultarPeriodoActivo(@QueryParam("nombreUsuario") String nombreUsuario) throws RemoteException {
		Simulacion periodo = null;
		Simulacion respuesta = new Simulacion();
		try {
			periodo = simulacionBL.obtenerPeriodoActivo(nombreUsuario);
			respuesta.setId(periodo.getId());
			respuesta.setFechaInicio(periodo.getFechaInicio());
			respuesta.setFechaFin(periodo.getFechaFin());
			respuesta.setSaldo(periodo.getSaldo());
		} catch (MyException e) {
			throw new RemoteException("Error obteniendo el periodo de simulaci�n con ese Id", e);
		} catch(NullPointerException e) {
			throw new RemoteException("El periodo especificado no existe en el sistema");
		}
		return respuesta;
	}
}
