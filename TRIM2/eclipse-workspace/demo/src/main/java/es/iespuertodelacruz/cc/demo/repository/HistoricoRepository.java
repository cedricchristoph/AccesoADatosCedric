package es.iespuertodelacruz.cc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.iespuertodelacruz.cc.demo.entity.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Integer>{

}
