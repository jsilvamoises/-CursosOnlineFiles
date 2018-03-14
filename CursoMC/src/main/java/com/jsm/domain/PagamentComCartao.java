package com.jsm.domain;

import javax.persistence.Entity;

import com.jsm.domain.enums.EstadoPagamento;

@Entity
public class PagamentComCartao extends Pagamento {
	
	private static final long serialVersionUID = 1L;
	private Integer numeroParcelas;

	public PagamentComCartao() {
		super();
		
	}

	public PagamentComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	
	
	
	
}
