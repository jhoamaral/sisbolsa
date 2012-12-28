package br.com.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * The persistent class for the matricula database table.
 * 
 */
@Entity
@Cacheable
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="data_ingresso")
	private Date dataIngresso;
    
	@Column(name="duracao_curso")
	private Integer duracaoCurso;

	private String matricula;

	//bi-directional many-to-one association to Cursofaculdade
    @ManyToOne
	@JoinColumn(name="cursofaculdadeid")
	private Cursofaculdade cursofaculdade;
    
    @ManyToOne
	@JoinColumn(name="periodoletivoid")
	private Periodoletivo periodoletivo;

	//bi-directional many-to-one association to Pessoa
    @ManyToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;

	//bi-directional many-to-one association to Matriculaperiodo
	@OneToMany(mappedBy="matricula",fetch=FetchType.LAZY)
	private Set<Matriculaperiodo> matriculaperiodos;

    public Matricula() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataIngresso() {
		return this.dataIngresso;
	}

	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public Periodoletivo getPeriodoletivo() {
		return periodoletivo;
	}

	public void setPeriodoletivo(Periodoletivo periodoletivo) {
		this.periodoletivo = periodoletivo;
	}

	public Integer getDuracaoCurso() {
		return this.duracaoCurso;
	}

	public void setDuracaoCurso(Integer duracaoCurso) {
		this.duracaoCurso = duracaoCurso;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Cursofaculdade getCursofaculdade() {
		return this.cursofaculdade;
	}

	public void setCursofaculdade(Cursofaculdade cursofaculdade) {
		this.cursofaculdade = cursofaculdade;
	}
	
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Set<Matriculaperiodo> getMatriculaperiodos() {
		return this.matriculaperiodos;
	}

	public void setMatriculaperiodos(Set<Matriculaperiodo> matriculaperiodos) {
		this.matriculaperiodos = matriculaperiodos;
	}
	
}