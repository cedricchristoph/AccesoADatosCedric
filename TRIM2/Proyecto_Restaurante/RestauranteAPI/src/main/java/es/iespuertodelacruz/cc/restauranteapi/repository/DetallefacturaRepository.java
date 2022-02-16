package es.iespuertodelacruz.cc.restauranteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;

@Repository
public interface DetallefacturaRepository extends JpaRepository<Detallefactura, Integer>{

}
