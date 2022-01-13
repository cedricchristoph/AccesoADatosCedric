package es.iespuertodelacruz.jc.cambiomonedas.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericServiceInterface<T,E> {

	Iterable<T> findAll();
	Page<T> findAll(Pageable page);
	Optional<T> findById(E id);
	T save(T objeto);
	void deleteById(E id);
	void delete(T entity);
}
