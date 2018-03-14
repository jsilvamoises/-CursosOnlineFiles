package com.jsm.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	// DADOS PESSOAIS
	@NotEmpty
	@Email
	@Column(name="email",unique=true)
	private String email;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	@Column(name="cpf_cnpj",unique=true)
	private String cpfCnpj;
	
	@NotNull
	private Integer tipo;
	// TELEFONES
	@NotEmpty
	private String Telefone1;
	private String telefone2;
	private String telefone3;
	// ENDERECO
	
	@NotEmpty
	private String logradouro;
	@NotEmpty
	private String numero;
	private String complemento;
	
	@NotEmpty
	private String bairro;
	
	@NotEmpty
	private String cep;
	
	@NotNull
	private Long cidadeId;
	public ClienteNewDTO() {
		super();
		
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
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getTelefone1() {
		return Telefone1;
	}
	public void setTelefone1(String telefone1) {
		Telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getTelefone3() {
		return telefone3;
	}
	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Long getCidadeId() {
		return cidadeId;
	}
	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	

}
