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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jsm.domain.Cliente;
import com.jsm.dto.ClienteDTO;
import com.jsm.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")

public class ClienteResource {

	@Autowired
	ClienteService service;


	@Autowired
	BCryptPasswordEncoder encoder;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> get(@PathVariable Long id) {
		Cliente cat = service.get(id);
		return ResponseEntity.ok(cat);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> get() {
		List<Cliente> listObj = service.get();
		List<ClienteDTO> listDTO = listObj.stream().map(dto -> new ClienteDTO(dto)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/p")
	ResponseEntity<Page<Cliente>> get(Pageable pageable) {
		Page<Cliente> page = service.get(pageable);
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<Void> post(@Valid @RequestBody Cliente dto) {
		System.out.println("DTO " + dto.toString());

		dto.setPassword(encoder.encode(dto.getPassword()));

		service.post(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> put(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {

		System.out.println(id + " - " + dto);
		Cliente obj = service.put(id, service.fromDTO(dto));

		return ResponseEntity.ok(new ClienteDTO(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted!!!");
	}

	@PostMapping("/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile multipartFile) {
		URI uri = service.uploadImage(multipartFile);
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/email")
	public ResponseEntity<Cliente> get(@RequestParam("value") String value){
		Cliente cliente = service.findByEmail(value);
		return ResponseEntity.ok(cliente);
	}
	

}
