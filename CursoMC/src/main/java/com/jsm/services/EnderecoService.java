package com.jsm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsm.domain.Endereco;
import com.jsm.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;
	
	@Autowired
	private ClienteService cliService;
	

	
	
	public List<Endereco> enderecosPorEmail(String email){
		return repo.listByEmail(email);
	}
	
	public void cadastrarNovoEndereco(String email) {
		
	}
}
