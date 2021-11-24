package es.iespuertodelacruz.cc.repositories;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import es.iespuertodelacruz.cc.contracts.CustomerEntry;
import es.iespuertodelacruz.cc.webapprental.entity.Customer;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;

public class CustomerRepository extends CustomerEntry implements CRUD<Customer, Integer>{

	
	private EntityManagerFactory factory;
	
	public CustomerRepository(EntityManagerFactory emf) {
		this.factory = emf;
	}
	
	/**
	 * Devuelve un Customer con un ID especifico
	 */
	@Override
	public Customer select(Integer id) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYID);
		q.setParameter(1, id);
		Customer result = (Customer) q.getSingleResult();
		manager.getTransaction().commit();
		return result;
	}

	/**
	 * Devuelve una lista de resultados filtrado por nombre y apellidos
	 * @param name Nombre
	 * @param lastname Apellidos
	 * @return List<Customer> resultados
	 */
	public List<Customer> selectByFullname (String name, String lastname) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYFULLNAME);
		q.setParameter(1, "%" + name + "%");
		q.setParameter(2, "%" + lastname + "%");
		List<Customer> result = (List<Customer>) q.getResultList();
		manager.getTransaction().commit();
		return result;
	}
	
	/**
	 * Devuelve una lista de resultados filtrado por nombre
	 * @param name
	 * @return List<Customer> resultados
	 */
	public List<Customer> selectByName(String name) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYNAME);
		q.setParameter(1, "%" + name + "%");
		List<Customer> result = (List<Customer>) q.getResultList();
		manager.getTransaction().commit();
		return result;
	}
	
	/**
	 * Devuelve una lista de resultados filtrado por apellidos
	 * @param lastname
	 * @return List<Customer> resultados
	 */
	public List<Customer> selectByLastName(String lastname) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYLASTNAME);
		q.setParameter(1, "%" + lastname + "%");
		List<Customer> result = (List<Customer>) q.getResultList();
		manager.getTransaction().commit();
		return result;
	}
	
	/**
	 * Devuelve todos los Customer en la base de datos
	 */
	@Override
	public List<Customer> selectAll() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDALL);
		List<Customer> result = (List<Customer>) q.getResultList();
		manager.getTransaction().commit();
		return result;
	}

	@Override
	public Customer insert(Customer entity) throws SQLException {
		return null;
	}

	@Override
	public boolean update(Customer entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
