package es.iespuertodelacruz.cedric.tarea21enero.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cedric.tarea21enero.modelo.entity.Asiento;


/**
 * Asiento Repository
 * @author Cedric Christoph
 *
 */
@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Integer> {

	
}
