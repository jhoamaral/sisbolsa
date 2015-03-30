package br.com.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the diasaula database table.
 * 
 */
@Entity
public class Diasaula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Integer dia;
	
	@ManyToOne
	@JoinColumn(name="matriculaid")
	private Matricula matricula;

	public Diasaula() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDia() {
		return this.dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

}