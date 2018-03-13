package com.jsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

}
