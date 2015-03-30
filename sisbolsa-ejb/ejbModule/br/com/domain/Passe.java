package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the passe database table.
 * 
 */
@Entity
public class Passe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Integer mes;

	private Boolean recebido;

	//bi-directional many-to-one association to Diaspasse
	@OneToMany(mappedBy="passe")
	private Set<Diaspasse> diaspasses;

	//bi-directional many-to-one association to Ativopasse
	@ManyToOne
	@JoinColumn(name="ativopasseid")
	private Ativopasse ativopasse;

	public Passe() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getMes() {
		return this.mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Boolean getRecebido() {
		return this.recebido;
	}

	public void setRecebido(Boolean recebido) {
		this.recebido = recebido;
	}

	public Set<Diaspasse> getDiaspasses() {
		return this.diaspasses;
	}

	public void setDiaspasses(Set<Diaspasse> diaspasses) {
		this.diaspasses = diaspasses;
	}

	public Ativopasse getAtivopasse() {
		return this.ativopasse;
	}

	public void setAtivopasse(Ativopasse ativopasse) {
		this.ativopasse = ativopasse;
	}

}