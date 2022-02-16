package es.iespuertodelacruz.cc.restauranteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {

}
