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

import co.edu.udea.iw.bl.UsuarioBL;
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
	private UsuarioBL usuarioBL;
	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.UsuarioBLImp#registrar(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Prueba que valida el registro de un usuario
	 */
	@Test
	@Rollback(false)	
	public void testRegistrar() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1990);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.DAY_OF_MONTH, 4);
		Date fecha = calendar.getTime();		
		
		try{
			usuarioBL.registrar("elver","CC","1036987451","Elver",
					"Suarez",fecha,"elveer@gmail.com","123456");
			
		}catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * Prueba que valida la autenticacion del usuario
	 */
	@Test
	public void testAutenticar()
	{
		try{
			usuarioBL.autenticar("dcamoleaay", "cualquiercosa");
		}
		catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	
	/**
	 * Prueba para validar la edicion del email
	 */
	@Test
	@Rollback(false)
	public void testEditarEmail() {

		
		try{
			usuarioBL.editarEmail("s@gmail.com", "cualquiercosa", "pepe@gmail.com");
		}catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	
	/**
	 * Prueba para validar la edicion de la contraseña
	 */
	@Test
	@Rollback(false)	
	public void testEditarPassword() {

		
		try{
			usuarioBL.editarPassword("a@gmail.com", "cualquiercosa", "sergitoencriptado");
		}catch(MyException e)
		{
			fail(e.getMessage());
		}
	}
	
	

}
