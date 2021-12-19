package es.iespuertodelacruz.cc.institutorest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.cc.institutorest.entity.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

	
	//@Query("SELECT m FROM matriculas m WHERE m.dni = :dni")
	//List<Matricula> findByAlumno(@Param("dni")String paramDni);
	
}
