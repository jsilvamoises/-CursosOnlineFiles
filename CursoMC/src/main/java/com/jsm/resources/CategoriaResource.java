package com.jsm.resources;

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

import com.jsm.domain.Categoria;
import com.jsm.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")

public class CategoriaResource {

	@Autowired
	CategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> get(@PathVariable Long id) {
		Categoria cat = service.get(id);
		return ResponseEntity.ok(cat);
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> get() {
		List<Categoria> cats = service.get();
		return ResponseEntity.ok(cats);
	}

	@GetMapping("/p")
	ResponseEntity<Page<Categoria>> get(Pageable pageable) {
		Page<Categoria> page = service.get(pageable);
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
		categoria = service.post(categoria);
		return ResponseEntity.ok(categoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> put(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		categoria = service.put(id, categoria);
		return ResponseEntity.ok(categoria);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted!!!");
	}

}
