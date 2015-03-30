package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the diaspasse database table.
 * 
 */
@Entity
public class Diaspasse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	private Date dia;

	private Boolean usado;

	//bi-directional many-to-one association to Passe
	@ManyToOne
	@JoinColumn(name="passeid")
	private Passe passe;

	public Diaspasse() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDia() {
		return this.dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Boolean getUsado() {
		return this.usado;
	}

	public void setUsado(Boolean usado) {
		this.usado = usado;
	}

	public Passe getPasse() {
		return this.passe;
	}

	public void setPasse(Passe passe) {
		this.passe = passe;
	}

}