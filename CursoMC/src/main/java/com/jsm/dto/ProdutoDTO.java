package com.jsm.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jsm.domain.Produto;

public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotNull
	private BigDecimal preco;

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(@NotEmpty String nome, @NotNull BigDecimal preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}
	
	
	
	public ProdutoDTO(Long id, @NotEmpty String nome, @NotNull BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoDTO other = (ProdutoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdutoDTO [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
	}
	
	
}
