package es.iespuertodelacruz.cc.restauranteapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Operario;

@Repository
public interface OperarioRepository extends JpaRepository<Operario, Integer> {

	@Query("SELECT o FROM Operario o WHERE o.nombre = :nombre")
	public Operario selectByNombre(@Param("nombre") String strNombre);
	
}
