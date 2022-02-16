package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.cc.restauranteapi.entity.Operario;
import es.iespuertodelacruz.cc.restauranteapi.repository.OperarioRepository;

@Service
public class OperarioService implements GenericService<Operario, Integer> {

	
	@Autowired
	private OperarioRepository repo;

	@Override
	public Iterable<Operario> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Operario> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	public Optional<Operario> findByNombre(String nombre) {
		Operario o = repo.selectByNombre(nombre);
		if (o != null)
			return Optional.of(o);
		return Optional.empty();
	}
	
	@Override
	public Optional<Operario> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public Operario save(Operario producto) {
		return repo.save(producto);
	}

	@Override
	public void deleteById(Operario entity) {
		repo.delete(entity);
	}

	

}
