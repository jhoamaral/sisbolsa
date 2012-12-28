package br.com.domain;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the pessoabens database table.
 * 
 */
@Entity
@Table(name="pessoabens")
@Cacheable
public class Pessoaben implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	//bi-directional many-to-one association to Ben
    @ManyToOne
	@JoinColumn(name="fk_bensid")
	private Ben ben;

	//bi-directional many-to-one association to Pessoa
    @ManyToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;

    public Pessoaben() {
    	this.ben = new Ben();
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ben getBen() {
		return this.ben;
	}

	public void setBen(Ben ben) {
		this.ben = ben;
	}
	
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}