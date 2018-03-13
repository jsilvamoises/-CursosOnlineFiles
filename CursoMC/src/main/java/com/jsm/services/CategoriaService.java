package com.jsm.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.Categoria;
import com.jsm.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository rep;

	public Categoria get(Long id) {
		return rep.getOne(id);
	}

	public List<Categoria> get() {
		return rep.findAll();
	}

	public Page<Categoria> get(Pageable pageable) {
		return rep.findAll(pageable);
	}

	public Categoria post(Categoria categoria) {
		categoria = rep.save(categoria);
		return categoria;
	}

	public Categoria put(Long id, Categoria categoria) {
		Categoria savedCategoria = get(id);
		BeanUtils.copyProperties(categoria, savedCategoria, "id");
		categoria = rep.save(savedCategoria);
		return categoria;
	}
	
	public void delete(Long id) {
		rep.deleteById(id);
	}
}
