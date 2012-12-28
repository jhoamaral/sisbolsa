package br.com.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.service.UtilService;


/**
 * The persistent class for the matriculaperiodo database table.
 * 
 */
@Entity
@Cacheable
public class Matriculaperiodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private double cr;

	//bi-directional many-to-one association to Boleto
	@OneToMany(mappedBy="matriculaperiodo", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Boleto> boletos;

	//bi-directional many-to-one association to Eventofixo
	@OneToMany(mappedBy="matriculaperiodo",fetch=FetchType.LAZY)
	private Set<Eventofixo> eventofixos;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="matriculaperiodo",fetch=FetchType.LAZY)
	private Set<Item> items;

	//bi-directional many-to-one association to Matricula
    @ManyToOne
	@JoinColumn(name="matriculaid")
	private Matricula matricula;

	//bi-directional many-to-one association to Periodoletivo
    @ManyToOne
	@JoinColumn(name="periodoletivoid")
	private Periodoletivo periodoletivo;
    
	//bi-directional many-to-one association to Socioeconeomico
	@OneToOne(mappedBy="matriculaperiodo", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private Socioeconeomico socioeconeomico;

    public Matriculaperiodo() {
    	this.boletos = new HashSet<Boleto>();
    	this.socioeconeomico = new Socioeconeomico();
    	this.socioeconeomico.setId(UtilService.generateOid());
    	this.socioeconeomico.setMatriculaperiodo(this);
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Boleto> getBoletos() {
		return this.boletos;
	}

	public void setBoletos(Set<Boleto> boletos) {
		this.boletos = boletos;
	}
	
	public Set<Eventofixo> getEventofixos() {
		return this.eventofixos;
	}

	public void setEventofixos(Set<Eventofixo> eventofixos) {
		this.eventofixos = eventofixos;
	}
	
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	public double getCr() {
		return cr;
	}

	public void setCr(double cr) {
		this.cr = cr;
	}

	public Matricula getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public Periodoletivo getPeriodoletivo() {
		return this.periodoletivo;
	}

	public void setPeriodoletivo(Periodoletivo periodoletivo) {
		this.periodoletivo = periodoletivo;
	}
	
	public Socioeconeomico getSocioeconeomico() {
		return this.socioeconeomico;
	}

	public void setSocioeconeomico(Socioeconeomico socioeconeomico) {
		this.socioeconeomico = socioeconeomico;
	}
	
}