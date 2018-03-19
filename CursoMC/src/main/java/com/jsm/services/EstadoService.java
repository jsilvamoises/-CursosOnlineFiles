package com.jsm.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.mediastoredata.model.ObjectNotFoundException;
import com.jsm.domain.Estado;
import com.jsm.dto.EstadoDTO;
import com.jsm.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado post(EstadoDTO dto) {
		Estado estado = new Estado(dto);
		repo.save(estado);
		return estado;
	}
	
	public EstadoDTO get(Long id) {
		Optional<Estado> estado = repo.findById(id);
		if(!estado.isPresent()) {
			throw new ObjectNotFoundException("Estado não localizado com ID: "+id);
		}
		
		return estado.get().toDTO();
	}
	
	
	public List<EstadoDTO> get(){
		List<Estado> estadoList = repo.findAllByOrderByNome();
		return estadoList.stream().map(e -> e.toDTO()).collect(Collectors.toList());
	}
}
