package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the tipoevento database table.
 * 
 */
@Entity
@Cacheable
public class Tipoevento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="tipoevento",fetch=FetchType.LAZY)
	private Set<Evento> eventos;

    public Tipoevento() {
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

	public Set<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}
	
}