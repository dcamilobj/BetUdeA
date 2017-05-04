/**
 * 
 */
package co.edu.udea.iw.bl.imp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.ApuestaBL;
import co.edu.udea.iw.exception.MyException;

/**
 * @author user
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
	public void testRegistrar() {
		try{
		apuestaBL.registrar("Liga", "02-12-2010", 10000L, 2L, "Lakers", "dcamoleay");
		}
		catch(MyException e)
		{
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.imp.ApuestaBLImp#consultar(java.lang.Integer)}.
	 */
	@Test
	public void testConsultar() {
		fail("Not yet implemented");
	}

}
