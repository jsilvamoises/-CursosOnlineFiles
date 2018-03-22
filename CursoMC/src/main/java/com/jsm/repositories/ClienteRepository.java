package com.jsm.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jsm.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

	@Transactional(readOnly=true)
	public Optional<Cliente> findByEmail(String email);
	
	
	
	
}
