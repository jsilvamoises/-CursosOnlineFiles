package com.jsm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date instante;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")	
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")	
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="enderece_entrega_id")
	private Endereco enderecoEntrega;	
	
	
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> items  = new HashSet<>();

	public Pedido() {
		super();
		
	}

	public Pedido(Date instante, Cliente cliente, Endereco enderecoEntrega) {
		super();		
		this.instante = instante;		
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	
	@Transient
	public BigDecimal getValorTotal() {
		BigDecimal bd = new BigDecimal(0);
		for(ItemPedido ip:getItems()) {			
			bd = 	bd.add(ip.getSubTotal());
			System.out.println("BD To float: "+bd.floatValue());
		}
		
		return bd;
	}
	

	public Set<ItemPedido> getItems() {
		return items;
	}

	public void setItems(Set<ItemPedido> itens) {
		this.items = itens;
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
		Pedido other = (Pedido) obj;
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
		builder.append("\nPedido Nº ");
		builder.append(getId());
		builder.append("\ninstante: ");
		builder.append(instante);
		builder.append("\nSit Pagamento: ");
		builder.append(pagamento.getEstado().getDescricao());
		builder.append("\nCliente: ");
		builder.append(cliente.getNome());
		
		if(enderecoEntrega!=null) {
			builder.append("\nEnderecoEntrega: ");
			builder.append(enderecoEntrega.toString());
		}
		
		builder.append("\n####### ITENS DO PEDIDO #######\n");
		
		for(ItemPedido ip:getItems()) {
			builder.append(ip.toString());
		}
		builder.append("\nValor Total: ");
		builder.append(getValorTotal());
		return builder.toString();
	}
   
	
	
	
	

}
