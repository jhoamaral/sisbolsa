package br.com.domain;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * The persistent class for the titulo database table.
 * 
 */
@Entity
@Cacheable
public class Titulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String numero;

	private String secao;

	private String zona;

	//bi-directional many-to-one association to Pessoa
	@OneToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;
	
    public Titulo() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSecao() {
		return this.secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	
}