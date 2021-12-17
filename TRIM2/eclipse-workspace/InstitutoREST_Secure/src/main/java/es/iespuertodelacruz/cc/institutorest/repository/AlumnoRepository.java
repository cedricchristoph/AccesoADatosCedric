package es.iespuertodelacruz.cc.institutorest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.cc.institutorest.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, String>{

	@Query("SELECT a FROM Alumno a WHERE a.nombre LIKE :nombre")
	List<Alumno> findByNombre(@Param("nombre") String strNombre);
	
}

