package com.jsm.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

    @EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	
	private Double desconto;
	private Double quantidade;
	private Double preco;
	public ItemPedido() {
		super();
		
	}
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Double quantidade, Double preco) {
		super();
		
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	public ItemPedidoPK getId() {
		return id;
	}
	public void setId(ItemPedidoPK id) {
		this.id = id;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Transient
	@JsonIgnore
	public Produto getProduto() {
		// TODO: Verificar se esta certo
		return this.id.getProduto();
	}
	
	@JsonIgnore
	@Transient
	public Pedido getPedido() {
		// TODO: Verificar se esta certo
		return this.getPedido();
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
