package com.jsm.domain.enums;

public enum TipoCliente {
	PESSOA_FISICA(0, "Pessoa Física"), PESSOA_JURIDICA(1, "Pessoa Jurídica");

	public Integer codigo;
	private String desc;

	private TipoCliente(Integer codigo, String desc) {
		this.codigo = codigo;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static TipoCliente TipoClientToEnum(Integer codigo)  {
		if(codigo == null ) {
			return null;
		}
		
		for(TipoCliente tc:TipoCliente.values()) {
			if(tc.getCodigo() == codigo) {
				return tc;
			}
		}
		
		throw new RuntimeException("Código inválido! -> "+codigo+" <-");
		
		//return null;
	}

}
