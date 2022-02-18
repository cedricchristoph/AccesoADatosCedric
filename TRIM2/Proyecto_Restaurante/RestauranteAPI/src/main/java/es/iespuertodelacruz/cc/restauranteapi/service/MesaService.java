package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.repository.MesaRepository;
@Service
public class MesaService implements GenericService<Mesa, Integer> {

	@Autowired
	private MesaRepository repo;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Mesa> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Mesa> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Mesa> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	@Transactional
	public Mesa save(Mesa producto) {
		return repo.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Mesa entity) {
		repo.delete(entity);
	}

}
