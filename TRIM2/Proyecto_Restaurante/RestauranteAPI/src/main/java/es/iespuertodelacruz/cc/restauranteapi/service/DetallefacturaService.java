package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;
import es.iespuertodelacruz.cc.restauranteapi.repository.DetallefacturaRepository;

@Service
public class DetallefacturaService implements GenericService<Detallefactura, Integer> {

	@Autowired
	private DetallefacturaRepository repo;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Detallefactura> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Detallefactura> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Detallefactura> findById(Integer id) {
		return repo.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Detallefactura> findByIdServicio(Integer servicioid) {
		return repo.findByIdServicio(servicioid);
	}
	
	@Override
	@Transactional
	public Detallefactura save(Detallefactura producto) {
		return repo.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Detallefactura entity) {
		repo.delete(entity);
	}

}
