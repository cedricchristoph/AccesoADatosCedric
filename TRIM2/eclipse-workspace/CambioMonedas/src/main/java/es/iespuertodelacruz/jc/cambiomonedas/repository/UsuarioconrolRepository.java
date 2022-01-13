package es.iespuertodelacruz.jc.cambiomonedas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Usuarioconrol;

public interface UsuarioconrolRepository extends JpaRepository<Usuarioconrol, Integer> {
    @Query("SELECT t FROM Usuarioconrol t where t.nombre = :name") 
    List<Usuarioconrol> findByNombre(@Param("name") String strNombre);
}
