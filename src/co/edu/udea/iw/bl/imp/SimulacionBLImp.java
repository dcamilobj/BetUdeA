/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.SimulacionBL;
import co.edu.udea.iw.dao.SimulacionDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase para implementar todos los metodos de la logica del negocio
 * correspondiente a simulacion
 * 
 * @author Sergio Llanos
 * @version 1.0
 */
@Transactional
public class SimulacionBLImp implements SimulacionBL {

	private SimulacionDAO simulacionDAO;
	private UsuarioDAO usuarioDAO;
	
	public static final Double SALDO_INICIAL = 30000D;

	/**
	 * @return the simulacionDAO
	 */
	public SimulacionDAO getSimulacionDAO() {
		return simulacionDAO;
	}

	/**
	 * @param simulacionDAO
	 *            the simulacionDAO to set
	 */
	public void setSimulacionDAO(SimulacionDAO simulacionDAO) {
		this.simulacionDAO = simulacionDAO;
	}

	/**
	 * @return the usuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * @param usuarioDAO
	 *            the usuarioDAO to set
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * Metodo para registrar un periodo en la base de datos
	 * 
	 * @param id
	 * @param usuario
	 * @param saldo
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws MyException
	 */
	public void registrarPeriodo(String nombreUsuario)
			throws MyException {		

		/* validar el usuario en la base de datos */
		Usuario usuario = usuarioDAO.obtener(nombreUsuario);
		if (usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}
		
		Simulacion simulacionActiva = simulacionDAO.obtenerPeriodoActivo(nombreUsuario);
		
		if(simulacionActiva.getSaldo() > 0) {
			throw new MyException("No se puede registrar el periodo si el"
					+ " saldo es mayor a 0");
		}
		
		/*Validar que no se registre mas de un periodo de simulacion por semana*/
		Long fechaInicioActivo = simulacionActiva.getFechaInicio().getTime();
		Long diferenciaFecha = System.currentTimeMillis() - fechaInicioActivo;		
		if(diferenciaFecha < 604800000) {
			throw new MyException("No se pueden registrar mas periodos de"
					+ " simulacion esta semana");
		}		

		Simulacion simulacion = new Simulacion();
		simulacion.setUsuario(usuario);
		simulacion.setSaldo(SALDO_INICIAL);
		simulacion.setFechaInicio(new Date());
		simulacion.setFechaFin(null);

		simulacionDAO.registrarPeriodo(simulacion);

	}

	/**
	 * Metodo para consultar la lista de periodos dado el nombre de usuario
	 * 
	 * @param nombreUsuario
	 * @throws MyException
	 */
	public List<Simulacion> obtenerPeriodos(String nombreUsuario) throws MyException {

		/* validar que el dato del usuario no sea vacio */
		if (nombreUsuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}
		return simulacionDAO.obtenerPeriodos(nombreUsuario);
	}

	/**
	 * Metodo para consultar un periodo de simulacion dado el nombre de usuario
	 * 
	 * @param usuario_id
	 * @throws MyException
	 */
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws MyException {
		/* validar que el dato del usuario no sea vacio */
		if (periodoSimulacionId == null) {
			throw new MyException("El periodo de simulaci�n no existe en el sistema");
		}
		return simulacionDAO.obtenerPeriodo(periodoSimulacionId);
	}

	/**
	 * Metodo para consultar el periodo de simulacion activo de un usuario
	 * 
	 * @param nombreUsuario
	 * @throws MyException
	 */
	public Simulacion obtenerPeriodoActivo(String nombreUsuario) throws MyException {
		/* validar que el dato del usuario no sea vacio */
		if (nombreUsuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}

		Simulacion periodo = simulacionDAO.obtenerPeriodoActivo(nombreUsuario);
		if (periodo == null) {
			throw new MyException("El periodo es nulo");
		}

		return periodo;

	}
}
