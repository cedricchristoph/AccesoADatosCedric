package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;
import es.iespuertodelacruz.cc.restauranteapi.repository.DetallefacturaRepository;

@Service
public class DetallefacturaService implements GenericService<Detallefactura, Integer> {

	@Autowired
	private DetallefacturaRepository repo;
	
	@Override
	public Iterable<Detallefactura> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Detallefactura> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Optional<Detallefactura> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public Detallefactura save(Detallefactura producto) {
		return repo.save(producto);
	}

	@Override
	public void deleteById(Detallefactura entity) {
		repo.delete(entity);
	}

}
