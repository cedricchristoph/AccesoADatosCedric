package es.iespuertodelacruz.cc.repositories;

import java.sql.SQLException;
import java.util.List;

import es.iespuertodelacruz.cc.contracts.StaffEntry;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class StaffRepository extends StaffEntry implements CRUD<Staff, Integer> {

	private EntityManagerFactory factory;
	
	public StaffRepository(EntityManagerFactory emf) {
		this.factory = emf;
	}

	@Override
	public Staff select(Integer id) {
		return null;
	}
	
	/**
	 * Devuelve un Staff con un usuario especifico
	 * @param user
	 * @return
	 */
	public Staff selectByUser(String user) {
		try {
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			Query q = manager.createNamedQuery(FINDUSER);
			q.setParameter(1, user);
			Staff result = (Staff) q.getSingleResult();
			manager.getTransaction().commit();
			return result; 
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Devuelve todos los objetos Staff de la base de datos
	 */
	@Override
	public List<Staff> selectAll() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDALL);
		List<Staff> result = (List<Staff>) q.getResultList();
		manager.getTransaction().commit();
		return result;
	}

	@Override
	public Staff insert(Staff entity) throws SQLException {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			manager.getTransaction().begin();
			manager.persist(entity.getAddress());
			manager.persist(entity.getPayments());
			manager.persist(entity.getRentals());
			manager.persist(entity);
			manager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			try {
				manager.close();
			} catch (Exception ex) {
			}
			return null;
		}
	}

	@Override
	public boolean update(Staff entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
