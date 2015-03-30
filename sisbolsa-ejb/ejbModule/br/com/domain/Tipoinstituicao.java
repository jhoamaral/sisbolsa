package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipoinstituicao database table.
 * 
 */
@Entity
public class Tipoinstituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	public Tipoinstituicao() {
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

}