package br.com.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the instituicao database table.
 * 
 */
@Entity
@Cacheable
public class Instituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String nome;

	private String sigla;

	@OneToMany(mappedBy="instituicao",fetch=FetchType.LAZY)
	private Set<Cursoinstituicao> cursoinstituicaos;
	
	@ManyToOne
	@JoinColumn(name="tipoinstituicaoid")
	private Tipoinstituicao tipoinstituicao;

    public Instituicao() {
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

	public Set<Cursoinstituicao> getCursoinstituicaos() {
		return this.cursoinstituicaos;
	}

	public void setCursoinstituicaos(Set<Cursoinstituicao> cursoinstituicaos) {
		this.cursoinstituicaos = cursoinstituicaos;
	}

	public Tipoinstituicao getTipoinstituicao() {
		return tipoinstituicao;
	}

	public void setTipoinstituicao(Tipoinstituicao tipoinstituicao) {
		this.tipoinstituicao = tipoinstituicao;
	}
	
}