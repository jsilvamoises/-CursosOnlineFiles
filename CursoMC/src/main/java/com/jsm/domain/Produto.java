package com.jsm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = -1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private BigDecimal preco;

	@JsonBackReference
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinTable(
			name="produto_categoria",
			joinColumns = @JoinColumn(name="produto_id"),
			inverseJoinColumns = @JoinColumn(name="categoria_id")
			
			)
	private List<Categoria> categorias = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens  = new HashSet<>();

	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Produto() {
		super();

	}
	
	

	public Produto(Long id, String nome, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	@JsonIgnore
	@Transient
	public List<Pedido> getPedidos(){
		// TODO: Verificar se está correto
		List<Pedido> lista  = new ArrayList<Pedido>();
		for(ItemPedido ip:itens) {
			lista.add(ip.getPedido());
		}
		
		return lista;
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
	
	

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
	}

}
