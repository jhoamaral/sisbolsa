package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the boleto database table.
 * 
 */
@Entity
@Cacheable
public class Boleto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

    @Temporal( TemporalType.DATE)
	private Date data;

	private BigDecimal valor;

	//bi-directional many-to-one association to Matriculaperiodo
    @ManyToOne
	@JoinColumn(name="matriculaperiodoid")
	private Matriculaperiodo matriculaperiodo;

    public Boleto() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Matriculaperiodo getMatriculaperiodo() {
		return this.matriculaperiodo;
	}

	public void setMatriculaperiodo(Matriculaperiodo matriculaperiodo) {
		this.matriculaperiodo = matriculaperiodo;
	}
	
}