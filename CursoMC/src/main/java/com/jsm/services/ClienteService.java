package com.jsm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.Cliente;
import com.jsm.domain.Cliente;
import com.jsm.repositories.ClienteRepository;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository rep;

	public Cliente get(Long id) {
		Optional<Cliente>  cat = rep.findById(id);
		if(!cat.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! "
					+ "ID: "+id+" | Tipo: "+ Cliente.class.getName());
			
		}
		return cat.get();
	}

	public List<Cliente> get() {
		return rep.findAll();
	}

	public Page<Cliente> get(Pageable pageable) {
		return rep.findAll(pageable);
	}

	public Cliente post(Cliente entity) {
		entity = rep.save(entity);
		return entity;
	}

	public Cliente put(Long id, Cliente entity) {
		Cliente savedEntity = get(id);
		BeanUtils.copyProperties(entity, savedEntity, "id");
		entity = rep.save(savedEntity);
		return entity;
	}
	
	public void delete(Long id) {
		rep.deleteById(id);
	}
}
