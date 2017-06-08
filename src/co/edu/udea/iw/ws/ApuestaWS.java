/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.ApuestaBL;
import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.exception.MyException;

/**
 * Servicio Web para la entidad Apuesta
 * @author Andr�s Ceballos S�nchez - andres.ceballoss@udea.edu.co
 * @version 1.0
 *
 */
@Path("apuestas")
@Component
public class ApuestaWS {
	
	@Autowired
	private ApuestaBL apuestaBL;
	
	/**
	 * Servicio web para registrar una apuesta de un evento deportivo
	 * http://localhost:8080/BetUdeA/apuestas?
	 * evento=Spurs vs Cleveland&fecha=2017-05-20&valor=4000&cuota=2.3&opcion=Spurs&usuario=usertest
	 * @param evento
	 * @param strFechaEvento
	 * @param valorApostado
	 * @param cuota
	 * @param opcionSeleccionada
	 * @param usuario
	 * @throws RemoteException
	 */
	@POST
	public void registrar(@QueryParam("evento") String evento, @QueryParam("fecha") String strFechaEvento,
						  @QueryParam("valor") Double valorApostado, @QueryParam("cuota") Double cuota,
						  @QueryParam("opcion") String opcionSeleccionada, @QueryParam("usuario") String usuario) throws RemoteException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaEvento = null;
		try {	
			fechaEvento = sdf.parse(strFechaEvento);
			apuestaBL.registrar(evento, fechaEvento, valorApostado, cuota, opcionSeleccionada, usuario);
		} catch(MyException e) {
			throw new RemoteException("Error registrando la apuesta");
		} catch(ParseException e) {
			throw new RemoteException("Error en la fecha ingresada");
		} catch(NullPointerException e) {
			
		}
	}
	
	/**
	 * Servicio web para consultar las apuestas dado un periodo de simulaci�n
	 * http://localhost:8080/BetUdeA/apuestas?periodo=...
	 * @param periodoSimulacion
	 * @return
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Apuesta> consultarApuestas(@QueryParam("periodo") Long periodoSimulacion) throws RemoteException {
		List<Apuesta> apuestas = null;
		List<Apuesta> resultado = new ArrayList<>();
		Apuesta apuestaWS = null;
		try {
			apuestas = apuestaBL.consultar(periodoSimulacion);
			for(Apuesta apuesta: apuestas) {
				apuestaWS = new Apuesta();
				apuestaWS.setId(apuesta.getId());
				apuestaWS.setEvento(apuesta.getEvento());
				apuestaWS.setFechaEvento(apuesta.getFechaEvento());				
				apuestaWS.setValorApostado(apuesta.getValorApostado());
				apuestaWS.setCuota(apuesta.getCuota());
				apuestaWS.setOpcionSeleccionada(apuesta.getOpcionSeleccionada());
				apuestaWS.setEstado(apuesta.getEstado());
				apuestaWS.setFechaApuesta(apuesta.getFechaApuesta());
				apuestaWS.setOpcionCorrecta(apuesta.getOpcionCorrecta());
				resultado.add(apuestaWS);
			}
		} catch(MyException e) {
			throw new RemoteException("Error consultando las apuestas");
		}
		
		return resultado;
	}
}
