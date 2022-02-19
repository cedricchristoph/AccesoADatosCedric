package es.iespuertodelacruz.cc.restauranteapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;

@Repository
public interface DetallefacturaRepository extends JpaRepository<Detallefactura, Integer>{

	@Query("SELECT d FROM Detallefactura d WHERE d.servicio.idservicio = :idservicio")
	public List<Detallefactura> findByIdServicio(@Param("idservicio") Integer idservicio);
	
	@Query("SELECT d FROM Detallefactura d WHERE d.plato.idplato = :idplato")
	public Optional<Detallefactura> findByPlatoId(@Param("idplato") Integer idplato);
	
	@Query("SELECT SUM(d.preciounidad*d.cantidad) FROM Detallefactura d WHERE d.servicio.idservicio = :idservicio")
	public Double getTotalAPagar(@Param("idservicio") Integer idservicio);
}
