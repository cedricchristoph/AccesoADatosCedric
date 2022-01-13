package es.iespuertodelacruz.jc.cambiomonedas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Historicocambioeuro;
import es.iespuertodelacruz.jc.cambiomonedas.repository.HistoricocambioeuroRepository;

@Service
public class HistoricocambioeuroService implements GenericServiceInterface<Historicocambioeuro, Integer> {

	@Autowired
	HistoricocambioeuroRepository historicocambioeuroRepository;
	
	@Override
	public Iterable<Historicocambioeuro> findAll() {
		
		return historicocambioeuroRepository.findAll();
	}

	@Override
	public Page<Historicocambioeuro> findAll(Pageable page) {
		
		return historicocambioeuroRepository.findAll(page);
	}

	@Override
	public Optional<Historicocambioeuro> findById(Integer id) {
		
		return historicocambioeuroRepository.findById(id);
	}

	@Override
	public Historicocambioeuro save(Historicocambioeuro objeto) {

		return historicocambioeuroRepository.save(objeto);
	}

	@Override
	public void deleteById(Integer id) {

		historicocambioeuroRepository.deleteById(id);
	}

	@Override
	public void delete(Historicocambioeuro entity) {

		historicocambioeuroRepository.delete(entity);
		
	}
}
