package com.jsm.repositories;


import java.util.List;

import javax.persistence.NamedQueries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jsm.domain.Categoria;
import com.jsm.domain.Produto;

@NamedQueries({
//	@NamedQuery(
//			name="ProdutoRepository.search",
//			query="SELECT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
})

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
	@Transactional(readOnly=true)
    @Query("SELECT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	public Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageable);
    
    
    @Transactional(readOnly=true)
    public Page<Produto> findDistincByNomeContainingAndCategoriasIn(String nome,List<Categoria> categorias,Pageable pageable);
    
    @Transactional(readOnly=true)
    public Page<Produto> findByCategoriasIdIn(Long ids[], Pageable pageable);
}
