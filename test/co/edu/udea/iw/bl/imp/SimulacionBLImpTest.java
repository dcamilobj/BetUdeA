/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
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
	 */
	@Test
	@Rollback(false)
	public void testIngresarPeriodo() {
		/*Deprecated
		Date fecha = new Date(17, 1, 1);
		Date fecha2= new Date(17,1,30); */
		
		LocalDate localDate = LocalDate.of(2017, 1, 1);
		Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		localDate = LocalDate.of(2017, 1, 30);
		Date fecha2 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		try{
			simulacionBL.ingresarPeriodo( 100001L, "dcamolaay", 30000L, fecha , null);
		}catch(MyException e)
		{
		    //e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.SimulacionBLImp#consultarPeriodos(String).
	 */
	@Test
	public void testConsultarPeriodos() {
		List<Simulacion> periodos = null;
		try{
			//periodos = simulacionBL.consultarPeriodos("10345607a");
			periodos = simulacionBL.consultarPeriodos("dcamolaay");
			assertNotNull(periodos);			
		}
		catch(MyException e)
		{
		    e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.SimulacionBLImp#obtenerPeriodo(Long).
	 */
	@Test
	public void testObtenerPeriodo() {
		Simulacion periodo = null;
		try{
			periodo = simulacionBL.obtenerPeriodo(100000L);
			assertNotNull(periodo);
		}
		catch(MyException e)
		{
		    e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.SimulacionBLImp#obtenerPeriodo(String).
	 */
	@Test
	public void testPeriodoActivo() {
		try{
			simulacionBL.periodoActivo("10345607a");
		}
		catch(MyException e)
		{
		    e.printStackTrace();
			fail(e.getMessage());
		}
	}


}
