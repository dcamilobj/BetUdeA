/**
 * 
 */
package co.edu.udea.iw.dao.imp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * 
 * @author Duban Camilo Bedoya Jiménez 
 * @version 1.0
 */
public class UsuarioDAOImp implements UsuarioDAO {

	
	private SessionFactory sessionFactory;
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Implementación de método para registrar un usuario en la base de datos.
	 * @param usuario
	 * @throws MyException
	 */
	@Override
	public void registrar(Usuario usuario) throws MyException {
		// TODO Auto-generated method stub
		Session session; 
		try{
			
			session = sessionFactory.getCurrentSession();
			session.save(usuario);
			
		}catch(HibernateException e)
		{
			throw new MyException("Error registrando el usuario.");
		}
	}

	/**
	 * Implementación de método para obtener un usuario de la base de datos dado su email.
	 * @param email
	 * @return usuario con el email dado
	 * @throws MyException 
	 */
	@Override
	public Usuario obtener(String email) throws MyException {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		Session session; 
		try{
			session= sessionFactory.getCurrentSession();
			usuario = (Usuario) session.get(Usuario.class, email);
			
		}catch(HibernateException e)
		{
			throw new MyException("Error consultando el usuario.");
		}
		
		return usuario;
	}

}
