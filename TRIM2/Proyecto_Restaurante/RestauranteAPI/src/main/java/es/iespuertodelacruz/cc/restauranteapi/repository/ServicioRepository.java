package es.iespuertodelacruz.cc.restauranteapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer>{

	@Query("SELECT s FROM Servicio s WHERE s.mesa.nummesa = :nummesa")
	public List<Servicio> findServiciosFromMesa(@Param("nummesa") Integer nummesa);
	
	//@Query("SELECT s FROM Servicio s WHERE s.mesa.nummesa = :nummesa AND s.fechacomienzo.longValue() < (select UNIX_TIMESTAMP() * 1000) AND pagada=0")
	@Query(
			value="SELECT * FROM servicio WHERE fknummesa=:nummesa AND fechacomienzo < (select UNIX_TIMESTAMP() * 1000) AND pagada=0 order by fechacomienzo desc limit 0,1;", 
			nativeQuery = true
	)
	public Optional<Servicio> findServicioActual(@Param("nummesa") Integer nummesa);

}
