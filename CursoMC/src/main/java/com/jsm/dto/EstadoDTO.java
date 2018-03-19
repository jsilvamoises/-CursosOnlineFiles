package com.jsm.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty
	private String nome;

	public EstadoDTO() {
		super();
		
	}

	public EstadoDTO(Long id,String nome) {
		super();
		this.nome = nome;
		this.id = id;
	}
	
	public EstadoDTO(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
