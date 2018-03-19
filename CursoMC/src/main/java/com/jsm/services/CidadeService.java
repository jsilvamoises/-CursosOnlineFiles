package com.jsm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.Cidade;
import com.jsm.dto.CidadeDTO;
import com.jsm.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<CidadeDTO> getByEstadoId(Long id){
		List<Cidade> cidades = repo.findByEstadoId(id);
		System.out.println(cidades);
		return cidades.stream().map(c -> c.toDTO()).collect(Collectors.toList());
	}
	
	public Page<CidadeDTO> cidades(Pageable pageabe){
		Page<Cidade> cidades = repo.findAll(pageabe);
		Page<CidadeDTO> dtos = cidades.map(c -> c.toDTO());
		return dtos;
	}
	
	
	public CidadeDTO cidadeById(Long id) {
		CidadeDTO cidade = repo.getOne(id).toDTO();
		return cidade;
	}
}
