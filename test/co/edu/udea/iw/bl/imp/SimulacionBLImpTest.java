/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.exception.MyException;

/**
 * Esta clase sirve para probar los metodos de la logica del negocio
 * @author Sergio Llanos
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional 
@ContextConfiguration(locations="classpath:SpringConfig.xml")
public class SimulacionBLImpTest {

	@Autowired
	private SimulacionBLImp simulacionBL;

	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.SimulacionBLImp#ingresarPeriodo(Long, String, Long, java.util.Date, java.util.Date).
	 * Prueba para registrar un periodo de simulacion
	 */
	@Test
	@Rollback(false)
	public void testIngresarPeriodo() {		
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date fecha = calendar.getTime();		
		
		try{
			simulacionBL.registrarPeriodo("elver");
		}catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.SimulacionBLImp#consultarPeriodos(String).
	 * Prueba para consultar los periodos de simulacion de un usuario
	 */
	@Test
	public void testConsultarPeriodos() {
		List<Simulacion> periodos = null;
		try{
			periodos = simulacionBL.obtenerPeriodos("dcamolaay");
			assertNotNull(periodos);			
		}
		catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.SimulacionBLImp#obtenerPeriodo(Long).
	 * Prueba para consultar un periodo de simulacion especifico
	 */
	@Test
	public void testObtenerPeriodo() {
		Simulacion periodo = null;
		try{
			periodo = simulacionBL.obtenerPeriodo(1L);
			assertNotNull(periodo);
		}
		catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.SimulacionBLImp#obtenerPeriodo(String).
	 * Prueba para consultar el periodo activo de un usuario
	 */
	@Test
	public void testPeriodoActivo() {
		Simulacion periodo = null;
		try{
			periodo = simulacionBL.obtenerPeriodoActivo("sergio");
			assertNotNull(periodo);
		}
		catch(MyException e)
		{
			fail(e.getMessage());
		}
	}


}
