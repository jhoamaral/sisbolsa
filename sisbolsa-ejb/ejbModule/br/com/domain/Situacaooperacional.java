package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the situacaooperacional database table.
 * 
 */
@Entity
@Cacheable
public class Situacaooperacional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	//bi-directional many-to-one association to Socioeconeomico
	@OneToMany(mappedBy="situacaooperacional",fetch=FetchType.LAZY)
	private Set<Socioeconeomico> socioeconeomicos;

    public Situacaooperacional() {
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

	public Set<Socioeconeomico> getSocioeconeomicos() {
		return this.socioeconeomicos;
	}

	public void setSocioeconeomicos(Set<Socioeconeomico> socioeconeomicos) {
		this.socioeconeomicos = socioeconeomicos;
	}
	
}