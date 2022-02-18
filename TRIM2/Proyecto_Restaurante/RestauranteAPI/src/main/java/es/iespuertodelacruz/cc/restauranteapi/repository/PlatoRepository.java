package es.iespuertodelacruz.cc.restauranteapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {

	@Query("SELECT p FROM Plato p WHERE p.disponible = :disponibilidad") 
	public List<Plato> findByAvailability(@Param("disponibilidad") Byte available);
	
}
