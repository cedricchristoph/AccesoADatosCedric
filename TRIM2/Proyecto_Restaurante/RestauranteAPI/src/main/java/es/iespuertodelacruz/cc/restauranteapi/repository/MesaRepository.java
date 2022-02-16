package es.iespuertodelacruz.cc.restauranteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer>{

}
