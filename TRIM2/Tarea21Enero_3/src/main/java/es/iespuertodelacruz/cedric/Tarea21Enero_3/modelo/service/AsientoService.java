package es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.entity.Asiento;
import es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.repository.AsientoRepository;

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

	public Iterable<Asiento> findConBusqueda(String input) {
		return repo.findByAsunto(input);
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
