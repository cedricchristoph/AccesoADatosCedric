package es.iespuertodelacruz.jc.cambiomonedas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;

public interface MonedasRepository extends JpaRepository<Monedas, Integer> {

}
