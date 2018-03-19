package com.jsm.resources;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.dto.CidadeDTO;
import com.jsm.services.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResorce {

	@Autowired
	private CidadeService service;
	
	@GetMapping
	public ResponseEntity<Page<CidadeDTO>> get(Pageable pageable){
		Page<CidadeDTO> cidades = service.cidades(pageable);		
		return ResponseEntity.ok(cidades);
	}
	
	
	@GetMapping("/estado/{id}")
	public ResponseEntity<List<CidadeDTO>> getByEstadoID(@PathVariable Long id){
		List<CidadeDTO> dtos = service.getByEstadoId(id);
		return ResponseEntity.ok(dtos);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CidadeDTO> getById(@PathVariable Long id){
		return ResponseEntity.ok(service.cidadeById(id));
	}
}
