package es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.entity.Asiento;


/**
 * Asiento Repository
 * @author Cedric Christoph
 *
 */
@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Integer> {

	@Query("SELECT a FROM Asiento a WHERE a.asunto LIKE :input")
	List<Asiento> findByAsunto(@Param("input") String input);
	
}
