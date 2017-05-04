/**
 * 
 */
package co.edu.udea.iw.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementacion de {@link UsuarioDAO}
 * @author Duban Camilo Bedoya Jiménez 
 * @see UsuarioDAO
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
		Session session = null; 
		try{
			
			session = sessionFactory.getCurrentSession();
			session.save(usuario);
			
		}catch(HibernateException e)
		{
			throw new MyException("Error registrando el usuario.");
		}
	}
	
	/**
	 * Definición de método para obtener un usuario de la base de datos dado su
	 * nombre de usuario.
	 * @param nombre de usuario
	 * @return usuario con el nombre de usuario dado
	 * @throws MyException
	 */
	@Override
	public Usuario obtener(String nombreUsuario) throws MyException {
		Usuario usuario = new Usuario();
		Session session = null; 
		try{
			session= sessionFactory.getCurrentSession();
			usuario = (Usuario) session.get(Usuario.class, nombreUsuario);
			
		}catch(HibernateException e)
		{
			throw new MyException("Error consultando el usuario.");
		}
		
		return usuario;
	}
	
	/**
	 * Implementación de método para obtener un usuario de la base de datos dado su email.
	 * @param email
	 * @return usuario con el email dado
	 * @throws MyException 
	 */
	@Override
	public Usuario obtenerPorEmail(String email) throws MyException {
		Usuario usuario = null;
		Session session = null;
		
		try{
			session= sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("email", email));
			usuario = (Usuario) criteria.uniqueResult();			
		}catch(HibernateException e)
		{
			throw new MyException("Error consultando el usuario por email.");
		}
		
		return usuario;
	}

}
