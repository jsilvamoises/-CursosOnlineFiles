package com.jsm.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsm.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long> {

	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id =:id ORDER BY obj.nome")
	public List<Cidade> findByEstadoId(@Param("id") Long id, Pageable pageable);
	
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id =:id ORDER BY obj.nome")
	public List<Cidade> findByEstadoId(@Param("id") Long id);
}
