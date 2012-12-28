package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Cacheable
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String complemento;

	private String numero;

	//bi-directional many-to-one association to Logradouro
    @ManyToOne
	@JoinColumn(name="logradouroid")
	private Logradouro logradouro;

	//bi-directional many-to-one association to Pessoa
    @ManyToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;

    public Endereco() {
    	this.logradouro = new Logradouro();
    	this.numero = "";
    	this.complemento = "";
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}