/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
		Date fecha = new Date(17, 1, 1);
		Date fecha2= new Date(17,1,30);
		try{
			simulacionBL.ingresarPeriodo( 100000L,"dcamoleay",30000L, fecha , null);
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
		try{
			simulacionBL.consultarPeriodos("10345607a");
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
		try{
			simulacionBL.obtenerPeriodo(2L);
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
