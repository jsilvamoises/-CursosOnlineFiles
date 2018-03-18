package com.jsm.security.enums;

public enum Perfil {
	ADMIN(1, "ROLE_ADMIN", "Administrador"), USER(2, "ROLE_USER", "Usuário");

	private Integer key;
	private String role;
	private String descricao;

	private Perfil(Integer key, String role, String descricao) {
		this.key = key;
		this.role = role;
		this.descricao = descricao;
	}

	public Integer getKey() {
		return key;
	}

	public String getRole() {
		return role;
	}

	public String getDescricao() {
		return descricao;
	}

}
