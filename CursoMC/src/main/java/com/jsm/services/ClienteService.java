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
import com.jsm.security.UserSS;
import com.jsm.security.enums.Perfil;
import com.jsm.security.service.UserService;
import com.jsm.security.service.exception.AuthorizationException;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository rep;

	@Autowired
	CidadeRepository cidadeRepository;

	public Cliente get(Long id) {
		UserSS user = UserService.autheticated();

		if (user == null || !user.hasHole(Perfil.ADMIN) || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado!");
		}

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
		// if(entity.getId() == null || entity.getId() == 0) {
		// entity.setPassword(encoder.encode(entity.getPassword()));
		// }
		entity = rep.save(entity);
		return entity;
	}

	public Cliente put(Long id, Cliente entity) {
		System.out.println("ID: >>" + id);
		Cliente savedEntity = get(id);
		System.out.println(entity);
		System.out.println(savedEntity);

		BeanUtils.copyProperties(entity, savedEntity, "id");
		System.out.println(entity);
		System.out.println(savedEntity);
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

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente();

		String[] telefones = { dto.getTelefone1(), dto.getTelefone2(), dto.getTelefone3() };

		Cidade cidade = cidadeRepository.getOne(dto.getCidadeId());
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(),
				dto.getBairro(), dto.getCep(), cidade, cliente);

		cliente.setCpfCnpj(dto.getCpfCnpj());
		cliente.setEmail(dto.getEmail());
		cliente.getEnderecos().add(endereco);
		cliente.setNome(dto.getNome());
		cliente.setPassword(dto.getPassword());
		cliente.setTipo(dto.getTipo().intValue());

		for (String t : telefones) {
			if (!t.trim().isEmpty()) {
				cliente.getTelefones().add(t);
			}
		}
		System.out.println("CLIENTE: " + cliente);
		return cliente;
	}

}
