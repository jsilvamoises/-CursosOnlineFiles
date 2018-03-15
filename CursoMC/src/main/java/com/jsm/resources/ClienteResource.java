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
import com.jsm.dto.ClienteDTO;
import com.jsm.dto.ClienteNewDTO;
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
	public ResponseEntity<List<ClienteDTO>> get() {
		List<Cliente> listObj = service.get();
		List<ClienteDTO> listDTO = listObj.stream().map(dto -> new ClienteDTO(dto)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}

	@GetMapping("/p")
	ResponseEntity<Page<Cliente>> get(Pageable pageable) {
		Page<Cliente> page = service.get(pageable);
		return ResponseEntity.ok(page);
	}

	

	@PostMapping
	public ResponseEntity<Void> post(@Valid @RequestBody ClienteNewDTO dto) {
		Cliente obj = service.fromDTO(dto);

		service.post(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(obj.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> put(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
		
		System.out.println(id +" - "+dto);
		Cliente obj = service.put(id, service.fromDTO(dto));
		
		return ResponseEntity.ok(new ClienteDTO(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted!!!");
	}

}
