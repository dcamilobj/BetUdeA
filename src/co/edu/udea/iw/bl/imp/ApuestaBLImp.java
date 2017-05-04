/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.ApuestaBL;
import co.edu.udea.iw.dao.ApuestaDAO;
import co.edu.udea.iw.dao.SimulacionDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase para implementar todos los metodos de la logica de negocio correspondiente a las apuestas
 * @author Andres Ceballos Sanchez
 *@version 1.0
 */
@Transactional
public class ApuestaBLImp implements ApuestaBL{

	private ApuestaDAO apuestaDAO;
	private UsuarioDAO usuarioDAO;
	private SimulacionDAO simulacionDAO;
	
	public static final Integer VALOR_MINIMO_APUESTA = 3000;
	
	
	/**
	 * @return the apuestaDAO
	 */
	public ApuestaDAO getApuestaDAO() {
		return apuestaDAO;
	}

	/**
	 * @param apuestaDAO the apuestaDAO to set
	 */
	public void setApuestaDAO(ApuestaDAO apuestaDAO) {
		this.apuestaDAO = apuestaDAO;
	}

	/**
	 * @return the usuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * @param usuarioDAO the usuarioDAO to set
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * @return the simulacionDAO
	 */
	public SimulacionDAO getSimulacionDAO() {
		return simulacionDAO;
	}

	/**
	 * @param simulacionDAO the simulacionDAO to set
	 */
	public void setSimulacionDAO(SimulacionDAO simulacionDAO) {
		this.simulacionDAO = simulacionDAO;
	}

	@Override
	public void registrar(String evento, String fechaEvento, Long valorApostado, Long cuota, String opcionSeleccionada,
			String usuarioId) throws MyException {
		
		/*Validar que la información de la apuesta no sea nula o este vacia*/
		if(evento == null || evento.isEmpty()) {
			throw new MyException("El evento de la apuesta no puede ser nulo o vacio");
		}
		
		if(fechaEvento == null || fechaEvento.isEmpty()) {
			throw new MyException("La fecha del evento es nula o esta vacia");
		}
		
		if(valorApostado == null) {
			throw new MyException("El valor de la apuesta no puede ser nulo");
		}
		
		if(cuota == null) {
			throw new MyException("La cuota de la apuesta no puede ser nula");
		}
		
		if(opcionSeleccionada == null || opcionSeleccionada.isEmpty()) {
			throw new MyException("La opción seleccionada es vacia o nula");
		}
		
		/*Validar que el usuario exista en la base de datos*/
		Usuario usuario = usuarioDAO.obtener(usuarioId);
		if(usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}
		
		if(valorApostado < VALOR_MINIMO_APUESTA) {
			throw new MyException("El valor apostado debe ser mayor a " + VALOR_MINIMO_APUESTA);
		}
		
		Simulacion periodoSimulacion = simulacionDAO.periodoActivo(usuarioId);
		if(periodoSimulacion == null) {
			throw new MyException("El usuario no tiene ningun periodo de simulación activo");
		}
		
		Long saldo = periodoSimulacion.getSaldo();
		
		if(valorApostado > saldo) {
			throw new MyException("El valor apostado no puede superar el saldo actual");
		}
		
		/*TODO: Validar formato fecha dd-MM-yyy*/
		Date fechaEventoDate = new Date();
		
		Apuesta apuesta = new Apuesta();
		apuesta.setEvento(evento);
		apuesta.setFechaApuesta(fechaEventoDate);
		apuesta.setValorApostado(valorApostado);
		apuesta.setCuota(cuota);
		apuesta.setOpcionSeleccionada(opcionSeleccionada);
		apuesta.setEstado("Abierta");
		apuesta.setFechaApuesta(new Date());
		apuesta.setPeriodoSimulacion(periodoSimulacion);
		apuesta.setOpcionCorrecta(null);
		
		apuestaDAO.registrar(apuesta);
		
		
	}

	@Override
	public List<Apuesta> consultar(Long periodoSimulacionId) throws MyException {
		
		if(periodoSimulacionId == null) {
			throw new MyException("El periodo de simulacion es nulo");
		}
		
		Simulacion periodoSimulacion = simulacionDAO.obtenerPeriodo(periodoSimulacionId);
		if(periodoSimulacion == null) {
			throw new MyException("El periodo de simulacion no existe en el sistema");
		}
		
		/*¿Debo validar que el periodo pertenezca al usuario dado?*/
		return apuestaDAO.consultar(periodoSimulacionId);
}

}
