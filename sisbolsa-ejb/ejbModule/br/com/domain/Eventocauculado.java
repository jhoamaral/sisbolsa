package br.com.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The persistent class for the eventocauculado database table.
 * 
 */
@Entity
@Cacheable
public class Eventocauculado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	private BigDecimal valor;

	//bi-directional many-to-one association to Evento
    @ManyToOne
	@JoinColumn(name="eventoid")
	private Evento evento;

	//bi-directional many-to-one association to Item
    @ManyToOne
	@JoinColumn(name="itemid")
	private Item item;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="usuarioid")
	private Usuario usuario;

    public Eventocauculado() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}