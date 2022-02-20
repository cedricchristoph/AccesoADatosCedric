package es.iespuertodelacruz.cc.restauranteapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer>{

	@Query(
			value = "SELECT * FROM mesas WHERE nummesa IN (SELECT DISTINCT m.nummesa FROM mesas m LEFT JOIN servicio s ON s.fknummesa=m.nummesa AND (s.fechacomienzo NOT BETWEEN :fechacomienzo AND :fechafin) AND s.pagada=1 WHERE m.ocupantesmax >= :ocupantes);",
			nativeQuery = true)
	public List<Mesa> findMesasLibres(
			@Param("fechacomienzo") long fechacomienzo,
			@Param("fechafin") long fechafin,
			@Param("ocupantes") int ocupantes);
	
}
