package es.iespuertodelacruz.cc.repositories;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import es.iespuertodelacruz.cc.contracts.PaymentEntry;
import es.iespuertodelacruz.cc.webapprental.entity.Payment;

public class PaymentRepository extends PaymentEntry implements CRUD<Payment, Integer>{

	private EntityManagerFactory factory;
	
	public PaymentRepository(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public Payment select(Integer id) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYID);
		q.setParameter(1, id);
		Payment result = (Payment) q.getSingleResult();
		//Hibernate.initialize(result.getPayments());
		manager.getTransaction().commit();
		return result;
	}
	
	public List<Payment> selectByCustomer(Integer customer_id) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYCUSTOMER);
		q.setParameter(1, customer_id);
		List<Payment> payments = q.getResultList();
		manager.getTransaction().commit();
		return payments;
	}
	@Override
	public List<Payment> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Payment insert(Payment entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(Payment entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
