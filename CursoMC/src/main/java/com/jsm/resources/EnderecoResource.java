package com.jsm.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.domain.Endereco;
import com.jsm.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;
	@GetMapping("/cliente-email")
	public ResponseEntity<List<Endereco>> enderecosPorClienteEmail(String email){
		List<Endereco> enderecos = service.enderecosPorEmail(email);
		return ResponseEntity.ok(enderecos);
	}

}
