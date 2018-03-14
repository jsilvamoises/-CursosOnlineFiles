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

import com.jsm.domain.Cliente;
import com.jsm.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")

public class ClienteResource {

	@Autowired
	ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> get(@PathVariable Long id) {
		Cliente cat = service.get(id);
		return ResponseEntity.ok(cat);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> get() {
		List<Cliente> cats = service.get();
		return ResponseEntity.ok(cats);
	}

	@GetMapping("/p")
	ResponseEntity<Page<Cliente>> get(Pageable pageable) {
		Page<Cliente> page = service.get(pageable);
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente) {
		cliente = service.post(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> put(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		cliente = service.put(id, cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted!!!");
	}

}
