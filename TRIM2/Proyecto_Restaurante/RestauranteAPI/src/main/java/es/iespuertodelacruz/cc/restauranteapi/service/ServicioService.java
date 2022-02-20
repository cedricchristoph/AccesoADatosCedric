package es.iespuertodelacruz.cc.restauranteapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.cc.restauranteapi.repository.DetallefacturaRepository;
import es.iespuertodelacruz.cc.restauranteapi.repository.ServicioRepository;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
@Service
public class ServicioService implements GenericService<Servicio, Integer>{

	@Autowired
	private ServicioRepository repo;
	
	@Autowired
	private DetallefacturaRepository repoDetalle;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Servicio> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Servicio> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Iterable<Servicio> findByMesa(Integer nummesa) {
		return repo.findServiciosFromMesa(nummesa);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Servicio> findById(Integer id) {
		return repo.findById(id);
	}

	@Transactional(readOnly=true)
	public Optional<Servicio> findServicioActual(Integer mesaid) {
		return repo.findServicioActual(mesaid);
	}
	
	@Transactional(readOnly=true)
	public Double getTotalAPagar(Integer idservicio) {
		return repoDetalle.getTotalAPagar(idservicio);
	}
	
	@Override
	@Transactional
	public Servicio save(Servicio producto) {
		return repo.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Servicio entity) {
		repo.delete(entity);
	}

}
