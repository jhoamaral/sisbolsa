package br.com.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * The persistent class for the periodoletivo database table.
 * 
 */
@Entity
@Cacheable
public class Periodoletivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Integer ano;

	private Integer semestre;
	
	//bi-directional many-to-one association to Folha
	@OneToMany(mappedBy="periodoletivo",fetch=FetchType.LAZY)
	private Set<Folha> folhas;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="periodoletivo",fetch=FetchType.LAZY)
	private Set<Matriculaperiodo> matriculasperiodo;

    public Periodoletivo() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return this.semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Set<Folha> getFolhas() {
		return this.folhas;
	}

	public void setFolhas(Set<Folha> folhas) {
		this.folhas = folhas;
	}

	public Set<Matriculaperiodo> getMatriculasperiodo() {
		return matriculasperiodo;
	}

	public void setMatriculasperiodo(Set<Matriculaperiodo> matriculasperiodo) {
		this.matriculasperiodo = matriculasperiodo;
	}
	
	
}