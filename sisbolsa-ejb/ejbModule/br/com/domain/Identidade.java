package br.com.domain;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * The persistent class for the identidade database table.
 * 
 */
@Entity
@Cacheable
public class Identidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String emissor;

	private String numero;

	//bi-directional many-to-one association to Pessoa
	@OneToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;

    public Identidade() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmissor() {
		return this.emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}