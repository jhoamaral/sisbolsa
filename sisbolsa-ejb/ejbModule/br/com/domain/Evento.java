package br.com.domain;

import java.io.Serializable;
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
 * The persistent class for the evento database table.
 * 
 */
@Entity
@Cacheable
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;
	
	private String codigo;

	//bi-directional many-to-one association to Tipoevento
    @ManyToOne
	@JoinColumn(name="tipoeventoid")
	private Tipoevento tipoevento;

	//bi-directional many-to-one association to Eventocauculado
	@OneToMany(mappedBy="evento", fetch=FetchType.LAZY)
	private Set<Eventocauculado> eventocauculados;

	//bi-directional many-to-one association to Eventofixo
	@OneToMany(mappedBy="evento" , fetch=FetchType.LAZY)
	private Set<Eventofixo> eventofixos;

	//bi-directional many-to-one association to Historicovalor
	@OneToMany(mappedBy="evento", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Historicovalor> historicovalors;

    public Evento() {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Tipoevento getTipoevento() {
		return this.tipoevento;
	}

	public void setTipoevento(Tipoevento tipoevento) {
		this.tipoevento = tipoevento;
	}
	
	public Set<Eventocauculado> getEventocauculados() {
		return this.eventocauculados;
	}

	public void setEventocauculados(Set<Eventocauculado> eventocauculados) {
		this.eventocauculados = eventocauculados;
	}
	
	public Set<Eventofixo> getEventofixos() {
		return this.eventofixos;
	}

	public void setEventofixos(Set<Eventofixo> eventofixos) {
		this.eventofixos = eventofixos;
	}
	
	public Set<Historicovalor> getHistoricovalors() {
		return this.historicovalors;
	}

	public void setHistoricovalors(Set<Historicovalor> historicovalors) {
		this.historicovalors = historicovalors;
	}
	
}