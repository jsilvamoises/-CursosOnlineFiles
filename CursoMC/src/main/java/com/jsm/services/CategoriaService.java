package com.jsm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jsm.domain.Categoria;
import com.jsm.repositories.CategoriaRepository;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository rep;

	public Categoria get(Long id) {
		Optional<Categoria>  cat = rep.findById(id);
		if(!cat.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! "
					+ "ID: "+id+" | Tipo: "+ Categoria.class.getName());
			
		}
		return cat.get();
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
		get(id);
		rep.deleteById(id);
	}

	@Deprecated
	public Page<Categoria> get(Integer pageNumber, Integer totalLines, String orderBy, String direction) {
		direction = direction.toUpperCase();
		PageRequest pageRequest = new PageRequest(pageNumber, totalLines, Direction.valueOf(direction), orderBy);
		return rep.findAll(pageRequest);
	}
}
