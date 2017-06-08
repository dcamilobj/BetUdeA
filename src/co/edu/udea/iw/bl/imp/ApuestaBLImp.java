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
	public static final String ESTADO_INCIAL = "Abierta";
	
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
	public void registrar(String evento, Date fechaEvento, Double valorApostado, Double cuota, String opcionSeleccionada,
			String nombreUsuario) throws MyException {
		
		System.out.println("Paso estas validaciones");
		/*Validar que la información de la apuesta no sea nula o este vacia*/
		if(evento == null || evento.isEmpty()) {
			throw new MyException("El evento de la apuesta no puede ser nulo o vacio");
		}
		
		if(fechaEvento == null || fechaEvento.toString().isEmpty()) {
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
		Usuario usuario = usuarioDAO.obtener(nombreUsuario);
		if(usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}
		
		/*Validar que el valor de la apuesta no sea menor al valor permitido*/
		if(valorApostado < VALOR_MINIMO_APUESTA) {
			throw new MyException("El valor apostado debe ser mayor a " + VALOR_MINIMO_APUESTA);
		}
		
		/*Obtener el periodo activo del usuario*/
		Simulacion periodoSimulacion = simulacionDAO.obtenerPeriodoActivo(nombreUsuario);
		if(periodoSimulacion == null) {
			throw new MyException("El usuario no tiene ningun periodo de simulación activo");
		} 
		System.out.println("Paso estas validaciones");
		/*Validar que el valor de la apuesta no supere el saldo del usuario*/
		Double saldo = periodoSimulacion.getSaldo();		
		if(valorApostado > saldo) {
			throw new MyException("El valor apostado no puede superar el saldo actual");
		}
		
		/*Validacion: 10 minutos antes de la hora del evento se deshabilita el evento*/
		Long fechaActual = System.currentTimeMillis();
		Long diferenciaFechas = fechaEvento.getTime() - fechaActual;
		if(diferenciaFechas < 600000) {
			throw new MyException("El evento ya no esta disponible");
		}
		
		Apuesta apuesta = new Apuesta();
		apuesta.setEvento(evento);
		apuesta.setFechaEvento(fechaEvento);
		apuesta.setValorApostado(valorApostado);
		apuesta.setCuota(cuota);
		apuesta.setOpcionSeleccionada(opcionSeleccionada);
		apuesta.setEstado(ESTADO_INCIAL);
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
		
		List<Apuesta> apuestas = apuestaDAO.consultar(periodoSimulacionId);
	
		if(apuestas.isEmpty()){
			throw new MyException("No hay apuestas en este periodo de simulacion");
		}
		
		
		/*¿Debo validar que el periodo pertenezca al usuario dado?*/
		return apuestas;
}

}
