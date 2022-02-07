package es.iespuertodelacruz.cedric.tarea21enero.modelo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.cedric.tarea21enero.modelo.entity.Asiento;
import es.iespuertodelacruz.cedric.tarea21enero.modelo.repository.AsientoRepository;

/**
 * Asiento Service
 * @author Cedric Christoph
 *
 */
@Service
public class AsientoService implements GenericService<Asiento, Integer> {

	@Autowired
	private AsientoRepository repo;
	
	@Override
	public Iterable<Asiento> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Asiento> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Optional<Asiento> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public Asiento save(Asiento producto) {
		return repo.save(producto);
	}

	@Override
	public void deleteById(Asiento entity) {
		repo.delete(entity);
	}

}
