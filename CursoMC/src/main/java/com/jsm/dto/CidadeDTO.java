package com.jsm.dto;

import java.io.Serializable;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private Long estadoId;

	public CidadeDTO() {
		super();

	}

	public CidadeDTO(String nome, Long estadoId) {
		super();
		this.nome = nome;
		this.estadoId = estadoId;
	}

	public CidadeDTO(Long id, String nome, Long estadoId) {
		super();
		this.id = id;
		this.nome = nome;
		this.estadoId = estadoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

}
