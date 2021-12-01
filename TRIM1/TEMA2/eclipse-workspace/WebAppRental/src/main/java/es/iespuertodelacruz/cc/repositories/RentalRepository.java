package es.iespuertodelacruz.cc.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import es.iespuertodelacruz.cc.contracts.RentalEntry;
import es.iespuertodelacruz.cc.webapprental.entity.Payment;
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
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			manager.getTransaction().begin();
			Rental find = manager.find(Rental.class,entity.getRentalId());
			ArrayList<Payment> newPayments = (ArrayList<Payment>) entity.getNewPayments();
			for (Payment p : newPayments) {
				manager.persist(p);
				find.addPayment(p);
			}
			entity.setNewPayments(null);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				manager.close();
			} catch (Exception e) {
				
			}
		}
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
