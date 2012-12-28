package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the bens database table.
 * 
 */
@Entity
@Table(name="bens")
@Cacheable
public class Ben implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	//bi-directional many-to-one association to Pessoaben
	@OneToMany(mappedBy="ben", fetch=FetchType.LAZY)
	private Set<Pessoaben> pessoabens;

    public Ben() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Pessoaben> getPessoabens() {
		return this.pessoabens;
	}

	public void setPessoabens(Set<Pessoaben> pessoabens) {
		this.pessoabens = pessoabens;
	}
	
}