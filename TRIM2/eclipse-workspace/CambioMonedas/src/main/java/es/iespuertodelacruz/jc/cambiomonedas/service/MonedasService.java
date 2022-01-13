package es.iespuertodelacruz.jc.cambiomonedas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;
import es.iespuertodelacruz.jc.cambiomonedas.repository.MonedasRepository;

@Service
public class MonedasService implements GenericServiceInterface<Monedas,Integer> {

	
	@Autowired
	MonedasRepository monedasRepository;
	
	@Override
	public Iterable<Monedas> findAll() {
		
		return monedasRepository.findAll();
	}

	@Override
	public Page<Monedas> findAll(Pageable page) {
		
		return monedasRepository.findAll(page);
	}

	@Override
	public Optional<Monedas> findById(Integer id) {
		
		return monedasRepository.findById(id);
	}

	@Override
	public Monedas save(Monedas objeto) {

		return monedasRepository.save(objeto);
	}

	@Override
	public void deleteById(Integer id) {

		monedasRepository.deleteById(id);
	}

	@Override
	public void delete(Monedas entity) {

		monedasRepository.delete(entity);
		
	}

}
