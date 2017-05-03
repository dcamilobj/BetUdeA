/**
 * 
 */
package co.edu.udea.iw.bl;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.imp.UsuarioBLImp;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * @author camilo
 *
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
		
		try{
			usuarioBL.registrar("10345607a","Camilo",
					"Bedoya","a@gmail.com","cualquiercosa");
			
		}catch(MyException e)
		{
		    //e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testAutenticar()
	{
		try{
			usuarioBL.autenticar("a@gmail.com", "cualquiercosa");
		}
		catch(MyException e)
		{
		    e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
