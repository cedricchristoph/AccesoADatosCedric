package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
import es.iespuertodelacruz.cc.restauranteapi.repository.PlatoRepository;

public class PlatoService  implements GenericService<Plato, Integer>{

	@Autowired
	private PlatoRepository repo;
	
	@Override
	public Iterable<Plato> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Plato> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Optional<Plato> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public Plato save(Plato producto) {
		return repo.save(producto);
	}

	@Override
	public void deleteById(Plato entity) {
		repo.delete(entity);
	}

}
