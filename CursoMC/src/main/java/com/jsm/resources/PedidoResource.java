package com.jsm.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jsm.domain.Pedido;
import com.jsm.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")

public class PedidoResource {

	@Autowired
	PedidoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> get(@PathVariable Long id) {
		Pedido cat = service.get(id);
		return ResponseEntity.ok(cat);
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> get() {
		List<Pedido> cats = service.get();
		return ResponseEntity.ok(cats);
	}

	@GetMapping("/p")
	ResponseEntity<Page<Pedido>> get(Pageable pageable) {
		Page<Pedido> page = service.get(pageable);
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido pedido) {
		pedido = service.post(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(pedido.getId()).toUri();

		return ResponseEntity.created(uri).body(pedido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedido> put(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
		pedido = service.put(id, pedido);
		return ResponseEntity.ok(pedido);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted!!!");
	}

}
