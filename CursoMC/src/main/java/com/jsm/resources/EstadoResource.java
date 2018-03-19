package com.jsm.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jsm.domain.Estado;
import com.jsm.dto.CidadeDTO;
import com.jsm.dto.EstadoDTO;
import com.jsm.services.CidadeService;
import com.jsm.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;

	@Autowired
	private CidadeService cidService;

	@PostMapping
	public ResponseEntity<Void> post(@Valid @RequestBody EstadoDTO dto) {
		Estado estado = service.post(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(estado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadoDTO> get(@PathVariable Long id) {
		EstadoDTO edto = service.get(id);
		return ResponseEntity.ok(edto);
	}

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> get() {
		return ResponseEntity.ok(service.get());
	}

	@GetMapping("/{id}/cidades")
	public ResponseEntity<List<CidadeDTO>> getByEstado(@PathVariable Long id) {
		List<CidadeDTO> cidades = cidService.getByEstadoId(id);
		return ResponseEntity.ok(cidades);
	}
}
