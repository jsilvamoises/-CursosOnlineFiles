package com.jsm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.Pedido;
import com.jsm.repositories.PedidoRepository;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository rep;

	public Pedido get(Long id) {
		Optional<Pedido>  cat = rep.findById(id);
		if(!cat.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! "
					+ "ID: "+id+" | Tipo: "+ Pedido.class.getName());
			
		}
		return cat.get();
	}

	public List<Pedido> get() {
		return rep.findAll();
	}

	public Page<Pedido> get(Pageable pageable) {
		return rep.findAll(pageable);
	}

	public Pedido post(Pedido entity) {
		entity = rep.save(entity);
		return entity;
	}

	public Pedido put(Long id, Pedido entity) {
		Pedido savedEntity = get(id);
		BeanUtils.copyProperties(entity, savedEntity, "id");
		entity = rep.save(savedEntity);
		return entity;
	}
	
	public void delete(Long id) {
		rep.deleteById(id);
	}
}
