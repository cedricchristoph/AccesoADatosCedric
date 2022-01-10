package es.iespuertodelacruz.cc.institutorest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.cc.institutorest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	
	
}
