package com.jsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.domain.Categoria;
import com.jsm.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
