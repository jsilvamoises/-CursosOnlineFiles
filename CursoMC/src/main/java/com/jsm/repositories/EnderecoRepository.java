package com.jsm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsm.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

	@Query("SELECT obj FROM Endereco obj WHERE obj.cliente.email like:email")
	public List<Endereco> listByEmail(@Param("email") String email);
}
