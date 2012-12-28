package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the tipotelefone database table.
 * 
 */
@Entity
@Cacheable
public class Tipotelefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	//bi-directional many-to-one association to Telefone
	@OneToMany(mappedBy="tipotelefone",fetch=FetchType.LAZY)
	private Set<Telefone> telefones;

    public Tipotelefone() {
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

	public Set<Telefone> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}
	
}