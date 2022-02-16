package es.iespuertodelacruz.cc.restauranteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;

public interface PlatoRepository extends JpaRepository<Plato, Integer> {

}
