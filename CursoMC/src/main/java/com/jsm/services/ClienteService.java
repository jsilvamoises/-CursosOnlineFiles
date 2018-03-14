package com.jsm.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.Cidade;
import com.jsm.domain.Cliente;
import com.jsm.domain.Endereco;
import com.jsm.dto.ClienteDTO;
import com.jsm.dto.ClienteNewDTO;
import com.jsm.repositories.CidadeRepository;
import com.jsm.repositories.ClienteRepository;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository rep;

	@Autowired
	CidadeRepository cidadeRepository;

	public Cliente get(Long id) {
		Optional<Cliente> cat = rep.findById(id);
		if (!cat.isPresent()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! " + "ID: " + id + " | Tipo: " + Cliente.class.getName());

		}
		return cat.get();
	}

	public List<Cliente> get() {
		return rep.findAll();
	}

	public Page<Cliente> get(Pageable pageable) {
		return rep.findAll(pageable);
	}

	public Cliente post(Cliente entity) {
		entity = rep.save(entity);
		return entity;
	}

	public Cliente put(Long id, Cliente entity) {
		Cliente savedEntity = get(id);
		BeanUtils.copyProperties(entity, savedEntity, "id");
		entity = rep.save(savedEntity);
		return entity;
	}

	public void delete(Long id) {
		get(id);
		rep.deleteById(id);
	}

	public Cliente fromDTO(@Valid ClienteDTO dto) {
		Cliente obj = get(dto.getId());
		obj.setEmail(dto.getEmail());
		obj.setNome(dto.getNome());
		return obj;
	}

	public Cliente fromDTO(@Valid ClienteNewDTO dto) {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();

		// Copia os dados do dto para o cliente e endereco
		BeanUtils.copyProperties(dto, cliente,"id");
		BeanUtils.copyProperties(dto, endereco,"id");
		
		// Vicula cliente e enderecos
		cliente.getEnderecos().add(endereco);
		endereco.setCliente(cliente);

		// Busca um cidade baseada no ID
		Cidade cidade = cidadeRepository.getOne(dto.getCidadeId());
		// Insere a cidade no Endereco
		endereco.setCidade(cidade);
		// Cria um array de strings com os telefones enviados via post
		String[] telefones = { dto.getTelefone1(), dto.getTelefone2(), dto.getTelefone3() };
		// Verifica se todos os telefones estão preenchidos para evitar de preencher com
		// vazio
		for (String t : telefones) {
			if (!t.trim().isEmpty()) {
				cliente.getTelefones().add(t);
			}
		}

		return cliente;
	}

}
