package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the logradouro database table.
 * 
 */
@Entity
@Cacheable
public class Logradouro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String bairro;

	private String cep;

	private String cidade;

	private String rua;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="logradouro")
	private Set<Endereco> enderecos;

    public Logradouro() {
    	this.bairro = "";
    	this.cep = "";
    	this.cidade = "";
    	this.rua = "";
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Set<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
}