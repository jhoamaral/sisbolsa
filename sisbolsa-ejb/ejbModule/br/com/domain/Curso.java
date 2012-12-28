package br.com.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@Cacheable
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	//bi-directional many-to-one association to Cursofaculdade
	@OneToMany(mappedBy="curso",fetch=FetchType.LAZY)
	private Set<Cursofaculdade> cursofaculdades;

    public Curso() {
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

	public Set<Cursofaculdade> getCursofaculdades() {
		return this.cursofaculdades;
	}

	public void setCursofaculdades(Set<Cursofaculdade> cursofaculdades) {
		this.cursofaculdades = cursofaculdades;
	}
	
}