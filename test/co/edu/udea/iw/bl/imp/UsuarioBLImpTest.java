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

import co.edu.udea.iw.bl.imp.UsuarioBLImp;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Duban Camilo Bedoya Jiménez
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional 
@ContextConfiguration(locations="classpath:SpringConfig.xml")
public class UsuarioBLImpTest {

	@Autowired
	private UsuarioBLImp usuarioBL;
	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.UsuarioBLImp#registrar(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	@Rollback(false)
	public void testRegistrar() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1991);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.DAY_OF_MONTH, 4);
		Date fecha = calendar.getTime();		
		
		try{
			usuarioBL.registrar("dcamoleaay","CC","10345697a","Camilo",
					"Bedoya",fecha,"a@gmail.com","cualquiercosa");
			
		}catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testAutenticar()
	{
		try{
			usuarioBL.autenticar("dcamolaay", "cualquiercosa");
		}
		catch(MyException e)
		{
		    //e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Rollback(false)
	public void testEditarEmail() {

		
		try{
			usuarioBL.editarEmail("a@gmail.com", "cualquiercosa", "b@gmail.com");
		}catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	

}
