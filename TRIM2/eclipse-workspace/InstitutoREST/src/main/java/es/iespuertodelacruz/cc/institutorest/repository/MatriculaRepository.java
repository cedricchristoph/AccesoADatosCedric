package es.iespuertodelacruz.cc.institutorest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.cc.institutorest.entity.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

}
