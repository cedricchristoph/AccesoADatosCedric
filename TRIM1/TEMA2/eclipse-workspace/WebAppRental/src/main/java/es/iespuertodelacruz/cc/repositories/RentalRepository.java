package es.iespuertodelacruz.cc.repositories;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import es.iespuertodelacruz.cc.contracts.RentalEntry;
import es.iespuertodelacruz.cc.webapprental.entity.Rental;

public class RentalRepository extends RentalEntry implements CRUD<Rental, Integer> {

	EntityManagerFactory factory;
	
	public RentalRepository(EntityManagerFactory emf) {
		factory = emf;
	}
	
	
	@Override
	public Rental select(Integer id) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYID);
		q.setParameter(1, id);
		Rental result = (Rental) q.getSingleResult();
		manager.getTransaction().commit();
		return result;
	}

	@Override
	public List<Rental> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental insert(Rental entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Rental entity) throws SQLException {
		try {
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
