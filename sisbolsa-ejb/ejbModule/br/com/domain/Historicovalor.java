package br.com.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The persistent class for the historicovalor database table.
 * 
 */
@Entity
@Cacheable
public class Historicovalor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Timestamp data;

	private BigDecimal valor;
	
	private Integer percentual;
	
	//bi-directional many-to-one association to Evento
    @ManyToOne
	@JoinColumn(name="eventoid")
	private Evento evento;

    public Historicovalor() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Integer getPercentual() {
		return percentual;
	}

	public void setPercentual(Integer percentual) {
		this.percentual = percentual;
	}
	
}