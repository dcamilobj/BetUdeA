/**
 * 
 */
package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApuestaJersey> consultar(@QueryParam("periodo") String periodo) throws RemoteException {
		List<Apuesta> apuestas = null;
		List<ApuestaJersey> resultado = new ArrayList<>();
		ApuestaJersey apuestaWS = null;
		try {
			Long periodoSimulacion = Long.valueOf(periodo);
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
