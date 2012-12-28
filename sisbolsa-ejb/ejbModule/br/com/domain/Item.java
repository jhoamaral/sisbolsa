package br.com.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Cacheable
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private BigDecimal valor;

	//bi-directional many-to-one association to Eventocauculado
	@OneToMany(mappedBy="item",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Eventocauculado> eventocauculados;

	//bi-directional many-to-one association to Folha
    @ManyToOne
	@JoinColumn(name="folhaid")
	private Folha folha;

	//bi-directional many-to-one association to Matriculaperiodo
    @ManyToOne
	@JoinColumn(name="matriculaperiodoid")
	private Matriculaperiodo matriculaperiodo;

    public Item() {
    	this.eventocauculados = new HashSet<Eventocauculado>();
    	this.valor = BigDecimal.ZERO;
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Set<Eventocauculado> getEventocauculados() {
		return this.eventocauculados;
	}

	public void setEventocauculados(Set<Eventocauculado> eventocauculados) {
		this.eventocauculados = eventocauculados;
	}
	
	public Folha getFolha() {
		return this.folha;
	}

	public void setFolha(Folha folha) {
		this.folha = folha;
	}
	
	public Matriculaperiodo getMatriculaperiodo() {
		return this.matriculaperiodo;
	}

	public void setMatriculaperiodo(Matriculaperiodo matriculaperiodo) {
		this.matriculaperiodo = matriculaperiodo;
	}
	
}