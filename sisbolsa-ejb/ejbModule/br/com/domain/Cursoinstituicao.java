package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the cursoinstituicao database table.
 * 
 */
@Entity
@Cacheable
public class Cursoinstituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Integer duracao;

	//bi-directional many-to-one association to Curso
    @ManyToOne
	@JoinColumn(name="cursoid")
	private Curso curso;

	//bi-directional many-to-one association to Instituicao
    @ManyToOne
	@JoinColumn(name="instituicaoid")
	private Instituicao instituicao;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="cursoinstituicao",fetch=FetchType.LAZY)
	private Set<Matricula> matriculas;

    public Cursoinstituicao() {
    	this.setCurso(new Curso());
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDuracao() {
		return this.duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Instituicao getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	public Set<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
}