package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the tipoparentesco database table.
 * 
 */
@Entity
@Cacheable
public class Tipoparentesco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	//bi-directional many-to-one association to Parente
	@OneToMany(mappedBy="tipoparentesco",fetch=FetchType.LAZY)
	private Set<Familiar> familiares;

    public Tipoparentesco() {
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

	public Set<Familiar> getParentes() {
		return this.familiares;
	}

	public void setParentes(Set<Familiar> parentes) {
		this.familiares = parentes;
	}
	
}