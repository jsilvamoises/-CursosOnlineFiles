package com.jsm.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.domain.Produto;
import com.jsm.dto.ProdutoDTO;
import com.jsm.resources.util.URLUtils;
import com.jsm.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")

public class ProdutoResource {

	@Autowired
	ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> get(@PathVariable Long id) {
		Produto obj = service.get(id);
		return ResponseEntity.ok(obj);
	}


	@GetMapping("/p")
	ResponseEntity<Page<ProdutoDTO>> get(
			Pageable pageable,
			@RequestParam(value="nome",defaultValue="") String nome, 
			@RequestParam(value="cats",defaultValue="") String cats){
		
		List<Long> catIds = URLUtils.decodeStringListToLong(cats);	
		
		String nomeDecoded = URLUtils.decodeParam(nome);
		
		Page<Produto> page = service.findDistincByNomeContainingAndCategoriasIn(nomeDecoded, catIds, pageable);
		
		Page<ProdutoDTO> listDTO = page.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok(listDTO);
	}
	
	
	@GetMapping("/categorias/{ids}")
	public ResponseEntity<Page<ProdutoDTO>> getByCategorias(@PathVariable String ids,Pageable pageable){
		Page<ProdutoDTO> dtos = service.byCategoriasIn(ids, pageable);
		
		return ResponseEntity.ok(dtos);
		
	}

	

}
