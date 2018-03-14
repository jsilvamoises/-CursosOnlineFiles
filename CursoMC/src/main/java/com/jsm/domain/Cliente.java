package com.jsm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jsm.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String nome;
	private String cpfCnpj;
	private Integer tipo;

	private List<Endereco> enderecos = new ArrayList<>();

	private Set<String> telefones = new HashSet<>();

	public Cliente(Long id, String email, String nome, String cpfCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.tipo = tipo.getCodigo();
	}

	public Cliente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.TipoClientToEnum(tipo);
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", email=" + email + ", nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", tipo=" + tipo
				+ "]";
	}

}
