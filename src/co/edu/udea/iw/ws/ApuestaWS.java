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
 * @author Andrés Ceballos Sánchez - andres.ceballoss@udea.edu.co
 * @version 1.0
 *
 */
@Path("apuesta")
@Component
public class ApuestaWS {
	
	@Autowired
	private ApuestaBL apuestaBL;
	
	/**
	 * http://localhost:8080/BetUdeA/BetUdeA/apuesta?evento=Spurs vs Cleveland&fecha=2017-05-18&valor=4000&cuota=2.3&opcion=Spurs&usuario=elver
	 * @param evento
	 * @param strFechaEvento
	 * @param valorApostado
	 * @param cuota
	 * @param opcionSeleccionada
	 * @param usuario
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String registrar(@QueryParam("evento") String evento, @QueryParam("fecha") String strFechaEvento,
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
		}
		
		return "Apuesta registrada exitosamente";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApuestaJersey> consultar(@QueryParam("periodo") Long periodoSimulacion) throws RemoteException {
		List<Apuesta> apuestas = null;
		List<ApuestaJersey> resultado = new ArrayList<>();
		ApuestaJersey apuestaWS = null;
		try {
			apuestas = apuestaBL.consultar(periodoSimulacion);
			for(Apuesta apuesta: apuestas) {
				apuestaWS = new ApuestaJersey();
				apuestaWS.setId(apuesta.getId());
				apuestaWS.setEvento(apuesta.getEvento());
				apuestaWS.setValorApostado(apuesta.getValorApostado());
				apuestaWS.setCuota(apuesta.getCuota());
				apuestaWS.setFechaApuesta(apuesta.getFechaApuesta());
				apuestaWS.setOpcionSeleccionada(apuesta.getOpcionSeleccionada());
				apuestaWS.setOpcionCorrecta(apuesta.getOpcionCorrecta());
				resultado.add(apuestaWS);
			}
		} catch(MyException e) {
			throw new RemoteException("Error consultando las apuestas");
		}
		
		return resultado;
	}
}
