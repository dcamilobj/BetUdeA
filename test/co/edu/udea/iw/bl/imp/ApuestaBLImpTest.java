/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.ApuestaBL;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Andres Ceballos Sanchez - andres.ceballoss@udea.edu.co 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional 
@ContextConfiguration(locations="classpath:SpringConfig.xml")
public class ApuestaBLImpTest {

	@Autowired
	private ApuestaBL apuestaBL;
	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.ApuestaBLImp#registrar(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Integer)}.
	 */
	@Test
	@Rollback(false)
	public void testRegistrar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.MAY);
		calendar.set(Calendar.DAY_OF_MONTH, 10);
		Date fecha = calendar.getTime();	
		try{			
			apuestaBL.registrar("Orsomarso vs America", fecha, 30000D, 2D, "Orsomarso", "dcamolaay");
		}
		catch(MyException e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.ApuestaBLImp#consultar(java.lang.Integer)}.
	 */
	@Test
	public void testConsultar() {
		
	}

}
