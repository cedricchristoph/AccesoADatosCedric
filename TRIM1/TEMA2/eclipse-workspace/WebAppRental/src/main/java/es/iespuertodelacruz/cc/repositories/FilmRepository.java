package es.iespuertodelacruz.cc.repositories;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import es.iespuertodelacruz.cc.contracts.FilmEntry;
import es.iespuertodelacruz.cc.webapprental.entity.*;

public class FilmRepository extends FilmEntry implements CRUD<Film, Integer> {

	EntityManagerFactory factory;
	
	public FilmRepository(EntityManagerFactory emf) {
		factory = emf;
	}
	
	@Override
	public Film select(Integer id) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDBYID);
		Film film = (Film) q.getSingleResult();
		manager.getTransaction().commit();
		return film;
	}

	@Override
	public List<Film> selectAll() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query q = manager.createNamedQuery(FINDALL);
		List<Film> result = (List<Film>) q.getResultList();
		manager.getTransaction().commit();
		return result;
	}

	@Override
	public Film insert(Film entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Film entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
