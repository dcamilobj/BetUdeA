/**
 * 
 */
package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.SimulacionDAO;
import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.dto.Simulacion;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Esta clase implementa los metodos definidos en la interfaz "SimulacionDAO"
 * @author Sergio Llanos
 * @version 1.0
 */
public class SimulacionDAOImp implements SimulacionDAO{

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
	 * Implementacion del metodo para registrar un periodo
	 * @param El usuario al cual se quiere ingresar un periodo de simulacion
	 * @throws MyException
	 */
	public void registrarPeriodo(Simulacion simulacion) throws MyException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.save(simulacion);
		} catch(HibernateException e) {
			throw new MyException("Error guardando el periodo", e);
		}
	};
	
	/**
	 * Implementacion del metodo para obtener los periodos de simulacion dado el usuario
	 * @return Retorna los periodos de simulacion de un usuario
	 * @param El usuario del cual se quiere obtener los periodos de simulacion
	 * @throws MyException
	 */
	public List<Simulacion> obtenerPeriodos (String nombreUsuario) throws MyException {
		List<Simulacion> periodos = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(Simulacion.class);
			criteria.add(Restrictions.eq("usuario.nombreUsuario", nombreUsuario));
			periodos = criteria.list();			
		} catch(HibernateException e) {
			throw new MyException("Error consultando la lista de periodos");
		}
		return periodos;
	};
	
	/**
	 * Implementacion del metodo para consultar un periodo de simulacion
	 * @return Retorna un periodo de simulacion de acuerdo al id del usuario
	 * @param El usuario del cual se quiere obtener el periodo de simulacion
	 * @throws MyException
	 */
	public Simulacion obtenerPeriodo(Long periodoSimulacionId) throws MyException{
		Simulacion periodo =new Simulacion();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			periodo= (Simulacion) session.get(Simulacion.class, periodoSimulacionId);
		} catch(HibernateException e) {
			throw new MyException("Error consultando la lista de periodos");
		}
		return periodo;
		
	}
	
	/**
	 * Implementacion del metodo para obtener el periodo activo dado un usuario
	 * @return Retorna el periodo de simulacion activo
	 * @param El usuario del cual se quiere obtener el periodo de simulacion
	 * @throws MyException
	 */
	public Simulacion obtenerPeriodoActivo(String nombreUsuario) throws MyException{
		Simulacion periodo = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(Simulacion.class);
			criteria.add(Restrictions.eq("usuario.nombreUsuario", nombreUsuario));
			criteria.add(Restrictions.isNull("fechaFin"));
			periodo = (Simulacion) criteria.uniqueResult();
		} catch(HibernateException e) {
			throw new MyException("Error obteniendo el periodo activo");
		}
		return periodo;
		
	}
	
	
}
