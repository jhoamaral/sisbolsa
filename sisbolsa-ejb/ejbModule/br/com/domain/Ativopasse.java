package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the ativopasse database table.
 * 
 */
@Entity
public class Ativopasse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="matriculaid")
	private Matricula matricula;

	//bi-directional many-to-one association to Tipotarifa
	@ManyToOne
	@JoinColumn(name="tipotarifaid")
	private Tipotarifa tipotarifa;

	//bi-directional many-to-one association to Passe
	@OneToMany(mappedBy="ativopasse")
	private Set<Passe> passes;

	public Ativopasse() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tipotarifa getTipotarifa() {
		return this.tipotarifa;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public void setTipotarifa(Tipotarifa tipotarifa) {
		this.tipotarifa = tipotarifa;
	}

	public Set<Passe> getPasses() {
		return this.passes;
	}

	public void setPasses(Set<Passe> passes) {
		this.passes = passes;
	}

}