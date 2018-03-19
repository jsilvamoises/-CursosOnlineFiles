package com.jsm.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jsm.domain.Pedido;
import com.jsm.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> get(@PathVariable Long id) {
		Pedido obj = service.get(id);
		return ResponseEntity.ok(obj);
	}
	
	
	@PostMapping
	public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido dto) {
		service.post(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	
	@GetMapping("/meus-pedidos")
	public ResponseEntity<Page<Pedido>> get(Pageable pageable){
		pageable.getSort().by("instante");
		pageable.getSort().descending();
		Page<Pedido> page = service.getByCliente(pageable);
	    return ResponseEntity.ok(page);
		
	}
}
