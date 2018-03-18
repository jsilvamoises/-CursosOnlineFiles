package com.jsm.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jsm.domain.Categoria;
import com.jsm.dto.CategoriaDTO;
import com.jsm.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")

public class CategoriaResource {

	@Autowired
	CategoriaService service;
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> get(@PathVariable Long id) {
		Categoria cat = service.get(id);
		return ResponseEntity.ok(cat);
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> get() {
		List<Categoria> listObj = service.get();
		List<CategoriaDTO> listDTO = listObj.stream().map(dto -> new CategoriaDTO(dto)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/p")
	ResponseEntity<Page<Categoria>> get(Pageable pageable) {
		Page<Categoria> page = service.get(pageable);
		return ResponseEntity.ok(page);
	}

	/**
	 * Exemplo de Uso:
	 * http://localhost:8080/categorias/paged?pageNumber=0&totalLines=1&orderBy=nome&direction=ASC
	 * 
	 * @param pageNumber
	 * @param totalLines
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Deprecated
	@GetMapping("/paged")
	ResponseEntity<Page<CategoriaDTO>> get(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "totalLines", defaultValue = "24") Integer totalLines,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> page = service.get(pageNumber, totalLines, orderBy, direction);

		Page<CategoriaDTO> pageDTO = page.map(p -> new CategoriaDTO(p));
		return ResponseEntity.ok(pageDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CategoriaDTO> post(@Valid @RequestBody CategoriaDTO dto) {
		Categoria obj = service.fromDTO(dto);

		service.post(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(obj.getId())
				.toUri();

		return ResponseEntity.created(uri).body(new CategoriaDTO(obj));
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> put(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
		Categoria obj = service.put(id, service.fromDTO(dto));
		return ResponseEntity.ok(new CategoriaDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted!!!");
	}

}
