package es.iespuertodelacruz.cc.restauranteapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;

@Repository
public interface DetallefacturaRepository extends JpaRepository<Detallefactura, Integer>{

	@Query("SELECT d FROM Detallefactura d WHERE d.servicio.idservicio = :idservicio")
	public List<Detallefactura> findByIdServicio(@Param("idservicio") Integer idservicio);
	
}
