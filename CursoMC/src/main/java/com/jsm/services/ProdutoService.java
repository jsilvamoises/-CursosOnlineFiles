package com.jsm.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.Categoria;
import com.jsm.domain.Produto;
import com.jsm.dto.ProdutoDTO;
import com.jsm.repositories.CategoriaRepository;
import com.jsm.repositories.ProdutoRepository;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository rep;
	
	@Autowired
	CategoriaRepository catRep;

	public Produto get(Long id) {
		Optional<Produto>  cat = rep.findById(id);
		if(!cat.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! "
					+ "ID: "+id+" | Tipo: "+ Produto.class.getName());
			
		}
		return cat.get();
	}

	public List<Produto> get() {
		return rep.findAll();
	}

	public Page<Produto> get(Pageable pageable) {
		return rep.findAll(pageable);
	}

	public Produto post(Produto entity) {
		entity = rep.save(entity);
		return entity;
	}

	public Produto put(Long id, Produto entity) {
		Produto savedEntity = get(id);
		BeanUtils.copyProperties(entity, savedEntity, "id");
		entity = rep.save(savedEntity);
		return entity;
	}
	
	public void delete(Long id) {
		get(id);
		rep.deleteById(id);
	}
	
	public Page<Produto> search(String nome,List<Long> ids, Pageable pageable){
		List<Categoria> categorias = catRep.findAllById(ids);
		return rep.search(nome, categorias, pageable);
	}
	
	public Page<Produto> findDistincByNomeContainingAndCategoriasIn(String nome,List<Long> ids,Pageable pageable){
		List<Categoria> categorias = catRep.findAllById(ids);
		return rep.findDistincByNomeContainingAndCategoriasIn(nome, categorias, pageable);
	}
	
	public Page<ProdutoDTO> byCategoriasIn(String ids, Pageable pageable){
		String array[] = ids.split(",");
		Long[]  longArray = new Long[array.length];
		for(int i = 0;i < array.length;i++) {
			longArray[i] = Long.valueOf(array[i]);
		}
		
		Page<Produto> produtos = rep.findByCategoriasIdIn(longArray, pageable);
		Page<ProdutoDTO> pageDto = produtos.map(p -> p.toDTO());
		return pageDto;
	}
	
	
}
