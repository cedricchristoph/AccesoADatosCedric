package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.iespuertodelacruz.cc.restauranteapi.repository.ServicioRepository;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;

public class ServicioService implements GenericService<Servicio, Integer>{

	@Autowired
	private ServicioRepository repo;
	
	@Override
	public Iterable<Servicio> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Servicio> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Optional<Servicio> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public Servicio save(Servicio producto) {
		return repo.save(producto);
	}

	@Override
	public void deleteById(Servicio entity) {
		repo.delete(entity);
	}

}
