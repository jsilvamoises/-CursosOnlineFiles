package com.jsm.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}


	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	
	public static EstadoPagamento estadoPorCodigo(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		
		for(EstadoPagamento ep:EstadoPagamento.values()) {
			if(ep.getCodigo() == codigo) {
				return ep;
			}
		}
		
		throw new RuntimeException("Codigo Inválido: >"+codigo+"<");
	}

}
