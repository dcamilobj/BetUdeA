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
 * Clase para implementar todos los metodos de la logica del negocio correspondiente a simulacion
 * @author Sergio Llanos
 * @version 1.0
 */
@Transactional
public class SimulacionBLImp implements SimulacionBL{
	
	private SimulacionDAO simulacionDAO;
	private UsuarioDAO usuarioDAO;

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


	/**
	 * Metodo para registrar un periodo en la base de datos,
	 * @param id
	 * @param usuario
	 * @param saldo
	 * @param fecha_inicio
	 * @param fecha_fin
	 * @throws MyException
	 */
	public void ingresarPeriodo(Long id, String usuario_id, Long saldo, 
			Date fecha_inicio, Date fecha_fin) throws MyException
	{
		
		/*Validar que la fecha_inicio del periodo no sea nulo o este vacia*/		
		if(fecha_inicio == null) {
			throw new MyException("La fecha de inicio no puede ser nula");
		}
		/*validar el usuario en la base de datos*/
		Usuario usuario = usuarioDAO.obtener(usuario_id);
		if(usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}
		/* validar que el periodo de finalizacion sea vacio
		 *  o que el saldo sea 0 y la fecha de inicializacion sea de hace 30 dias  */
		if(fecha_fin==null || saldo==0 && fecha_fin.getTime()-fecha_inicio.getTime()>=2592000000L ){
			
			Simulacion simulacion = new Simulacion();
			simulacion.setId(id);
			simulacion.setUsuario_id(usuario_id);
			simulacion.setSaldo(saldo);
			simulacion.setFecha_inicio(fecha_inicio);
			
			
			simulacionDAO.ingresarPeriodo(simulacion);
				
			}
	}
	
	/**
	 * Metodo para validar la consulta de una lista de periodos segun un id del usuario
	 * @param usuario_id
	 * @throws MyException
	 */
	public List<Simulacion> consultarPeriodos (String usuario_id) throws MyException{
		
		/*validar que el dato del usuario no sea vacio */
		if(usuario_id == null) {
			throw new MyException("El usuario no existe en el sistema");
		}
		return simulacionDAO.consultarPeriodos(usuario_id);
	}
	
	/**
	 * Metodo para validar consultar de un periodo segun un id del usuario
	 * @param usuario_id
	 * @throws MyException
	 */
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws MyException{
	/*validar que el dato del usuario no sea vacio */
	if(periodoSimulacionId == null) {
		throw new MyException("El periodo de simulación no existe en el sistema");
	}
	return simulacionDAO.obtenerPeriodo(periodoSimulacionId);
	}
	
	/**
	 * Metodo para validar consultar de un periodo segun un id del usuario
	 * @param usuario_id
	 * @throws MyException
	 */
	public Simulacion periodoActivo(String usuario_id) throws MyException{
	/*validar que el dato del usuario no sea vacio */
	if(usuario_id == null) {
		throw new MyException("El usuario no existe en el sistema");
	}
	
	
	return simulacionDAO.periodoActivo(usuario_id);
	}
}
