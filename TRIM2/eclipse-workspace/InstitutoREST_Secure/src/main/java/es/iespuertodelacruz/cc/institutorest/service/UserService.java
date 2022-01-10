package es.iespuertodelacruz.cc.institutorest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.cc.institutorest.entity.User;
import es.iespuertodelacruz.cc.institutorest.repository.UserRepository;

@Service
public class UserService implements GenericService<User, String> {

	
	@Autowired
	private UserRepository repo;
	
	@Override
	public Iterable<User> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Optional<User> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public User save(User producto) {
		return repo.save(producto);
	}

	@Override
	public void deleteById(User entity) {
		repo.delete(entity);
	}

}
