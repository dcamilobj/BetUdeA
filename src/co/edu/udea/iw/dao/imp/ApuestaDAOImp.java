package co.edu.udea.iw.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.ApuestaDAO;
import co.edu.udea.iw.dto.Apuesta;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementacion de {@link ApuestaDAO}
 * @author Andres Ceballos Sanchez - andres.ceballoss@udea.edu.co
 * @see ApuestaDAO
 * @version 1.0
 *
 */
public class ApuestaDAOImp implements ApuestaDAO {

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
	 * Implementacion del metodo registrar
	 * Registra una apuesta en la base de datos
	 * @param apuesta - apuesta a registrar
	 */
	@Override
	public void registrar(Apuesta apuesta) throws MyException {
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();
			session.save(apuesta);
		} catch(HibernateException e) {
			throw new MyException("Error guardando la apuesta", e);
		}
	}

	/**
	 * Implementacion del metodo consultar
	 * Retorna una lista de las apuestas dado un periodo de simulacion
	 * @return Lista de apuestas
	 */
	@Override
	public List<Apuesta> consultar(Long periodoSimulacion) throws MyException{
		List<Apuesta> apuestas = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(Apuesta.class);
			criteria.add(Restrictions.eq("periodo_simulacion_id", periodoSimulacion));
			apuestas = criteria.list();
		} catch(HibernateException e) {
			throw new MyException("Error consultando la lista de apuestas");
		}
		return apuestas;
	}

}
