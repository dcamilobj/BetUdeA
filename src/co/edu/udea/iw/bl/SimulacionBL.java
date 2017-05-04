package co.edu.udea.iw.bl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.exception.MyException;



/**
 * Clase para implementar todos los metodos de la logica de negocio correspondiente a una simulacion 
 * @author Sergio Llanos
 * @version 1.0
 */

@Transactional
public interface SimulacionBL {


	public void ingresarPeriodo(Long id, String usuario_id, Long saldo, Date fecha_inicio, Date fecha_fin) throws MyException;
	
	public List<Simulacion> consultarPeriodos (String usuario_id) throws MyException; 
	
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws MyException;
	
	public Simulacion periodoActivo(String usuario_id) throws MyException;
}
