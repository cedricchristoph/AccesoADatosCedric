package es.iespuertodelacruz.cc.restauranteapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer>{

	@Query("SELECT s FROM Servicio s WHERE s.mesa.nummesa = :nummesa")
	public List<Servicio> findServiciosFromMesa(@Param("nummesa") Integer nummesa);
	
}
