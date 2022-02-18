package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.cc.restauranteapi.entity.Operario;
import es.iespuertodelacruz.cc.restauranteapi.repository.OperarioRepository;

@Service
public class OperarioService implements GenericService<Operario, Integer> {

	
	@Autowired
	private OperarioRepository repo;

	@Override
	@Transactional(readOnly=true)
	public Iterable<Operario> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Operario> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Optional<Operario> findByNombre(String nombre) {
		Operario o = repo.selectByNombre(nombre);
		if (o != null)
			return Optional.of(o);
		return Optional.empty();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Operario> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	@Transactional
	public Operario save(Operario producto) {
		return repo.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Operario entity) {
		repo.delete(entity);
	}

	

}
