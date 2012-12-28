package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the faculdade database table.
 * 
 */
@Entity
@Cacheable
public class Faculdade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String nome;

	private String sigla;

	//bi-directional many-to-one association to Cursofaculdade
	@OneToMany(mappedBy="faculdade",fetch=FetchType.LAZY)
	private Set<Cursofaculdade> cursofaculdades;

    public Faculdade() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Set<Cursofaculdade> getCursofaculdades() {
		return this.cursofaculdades;
	}

	public void setCursofaculdades(Set<Cursofaculdade> cursofaculdades) {
		this.cursofaculdades = cursofaculdades;
	}
	
}