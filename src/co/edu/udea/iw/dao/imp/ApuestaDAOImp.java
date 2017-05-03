package co.edu.udea.iw.dao.imp;

import java.util.List;

import co.edu.udea.iw.dao.ApuestaDAO;
import co.edu.udea.iw.dto.Apuesta;

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
	 * Implementacion del metodo registrar(Apuesta)
	 * Registra una apuesta en la base de datos
	 * @param apuesta - apuesta a registrar
	 */
	@Override
	public void registrar(Apuesta apuesta) {
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
	public List<Apuesta> consultar(Integer periodoSimulacion) {
		List<Apuesta> apuestas = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = sessionFactory.getCurrentSession();
			// TODO: Buscar como seria la consulta
		} catch(HibernateException e) {
			throw new MyException("Error consultando la lista de apuestas");
		}
		return apuestas;
	}

}
