package com.jsm.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
    @JsonIgnore
    @EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	
	private BigDecimal desconto;
	private BigDecimal quantidade;
	private BigDecimal preco;
	public ItemPedido() {
		super();
		
	}
	public ItemPedido(Pedido pedido, Produto produto, BigDecimal desconto, BigDecimal quantidade, BigDecimal preco) {
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
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	public Produto getProduto() {		
		return this.id.getProduto();
	}
	
	@JsonIgnore
	public Pedido getPedido() {		
		return this.getPedido();
	}
	
	
	public void setPedido(Pedido pedido) {
	     this.id.setPedido(pedido);
	}
	
	public void setProduto(Produto produto) {
		this.id.setProduto(produto);
	}
	
	
	@Transient
	public BigDecimal getSubtotal() {
//		System.out.println("Preco:"+preco);
//		System.out.println("Desconto:"+desconto);
//		
//		System.out.println("Quantidade:"+quantidade);
//		System.out.println("SubTotal: "+(preco.subtract(desconto)).multiply(quantidade));
		BigDecimal bd = (preco.subtract(desconto)).multiply(quantidade);
//		System.out.println("BD: "+bd);
		return bd;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", QTD: ");
		builder.append(getQuantidade());
		builder.append(", PREÇO UNITÁRIO: ");
		builder.append(getPreco());
		builder.append(", SUBTOTAL: ");
		builder.append(getSubtotal());
		builder.append("\n");		
		return builder.toString();
	}
	
	
}
