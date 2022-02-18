package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
import es.iespuertodelacruz.cc.restauranteapi.repository.PlatoRepository;
@Service
public class PlatoService  implements GenericService<Plato, Integer>{

	@Autowired
	private PlatoRepository repo;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Plato> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Plato> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Plato> findById(Integer id) {
		return repo.findById(id);
	}

	@Transactional(readOnly=true)
	public Iterable<Plato> findByAvailability(Boolean available) {
		if (available)
			return repo.findByAvailability((byte) 1);
		return repo.findByAvailability((byte) 0);
	}

	@Override
	@Transactional
	public Plato save(Plato producto) {
		return repo.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Plato entity) {
		repo.delete(entity);
	}

}
