package br.com.domain;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The persistent class for the eventofixo database table.
 * 
 */
@Entity
@Cacheable
public class Eventofixo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	private Integer repeticao;

	private Boolean todosemestre;

	private Integer total;

	//bi-directional many-to-one association to Evento
    @ManyToOne
	@JoinColumn(name="eventoid")
	private Evento evento;

    @ManyToOne
	@JoinColumn(name="ativobolsaid")
	private Ativobolsa ativobolsa;

    public Eventofixo() {
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

	public Integer getRepeticao() {
		return this.repeticao;
	}

	public void setRepeticao(Integer repeticao) {
		this.repeticao = repeticao;
	}

	public Boolean getTodosemestre() {
		return this.todosemestre;
	}

	public void setTodosemestre(Boolean todosemestre) {
		this.todosemestre = todosemestre;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public Ativobolsa getAtivobolsao() {
		return this.ativobolsa;
	}

	public void setAtivobolsa(Ativobolsa ativobolsa) {
		this.ativobolsa = ativobolsa;
	}
	
}