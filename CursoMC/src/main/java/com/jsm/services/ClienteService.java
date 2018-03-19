package com.jsm.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsm.amazon.exception.FileException;
import com.jsm.amazon.service.ImageService;
import com.jsm.amazon.service.S3Service;
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
	
	
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String imgPrefix;
	
	@Value("${img.profile.size}")
	private int imageSize;
	
	@Autowired
	private S3Service s3Service;

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
	
	public URI uploadImage(MultipartFile mpf) {
		
		UserSS user = UserService.autheticated();
		if(user ==null) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(mpf);
		String fileNameOut = imgPrefix+user.getId()+".jpg";
		
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage  = imageService.resize(jpgImage, imageSize);
		
		try {
			return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileNameOut, "image");
		} catch (URISyntaxException e) {
			throw new FileException(e.getMessage(), e.getCause());
		}
		
		
		/* URI uri = upService.uploadFile(mpf);
		 * usado para salvar o nome da imagem no banco.
		Cliente cliente = get(user.getId());
		cliente.setFoto(uri.toString());
		put(cliente.getId(), cliente);
		*/
		
	}

	public Cliente findByEmail(String email) {
		Optional<Cliente> cli = rep.findByEmail(email);
		if(!cli.isPresent()) {
			throw new ObjectNotFoundException("Não foi encontrado cliente com esse email.");
		}
		return cli.get();
	}

}
