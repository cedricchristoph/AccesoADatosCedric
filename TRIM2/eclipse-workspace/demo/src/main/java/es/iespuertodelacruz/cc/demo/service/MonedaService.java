package es.iespuertodelacruz.cc.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.cc.demo.entity.Moneda;
import es.iespuertodelacruz.cc.demo.repository.MonedaRepository;

@Service
public class MonedaService implements GenericService<Moneda, Integer> {

	@Autowired
	private MonedaRepository repo;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Moneda> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Page<Moneda> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Optional<Moneda> findById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	@Transactional
	public Moneda save(Moneda producto) {
		// TODO Auto-generated method stub
		return repo.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Moneda entity) {
		// TODO Auto-generated method stub
		repo.delete(entity);
	}


}
