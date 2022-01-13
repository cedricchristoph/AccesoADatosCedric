package es.iespuertodelacruz.jc.cambiomonedas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Historicocambioeuro;

public interface HistoricocambioeuroRepository extends JpaRepository<Historicocambioeuro, Integer> {

}
