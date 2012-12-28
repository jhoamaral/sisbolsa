package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefone database table.
 * 
 */
@Entity
@Cacheable
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String dd;

	private String numero;

	//bi-directional many-to-one association to Pessoa
    @ManyToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;
    

	//bi-directional many-to-one association to Tipotelefone
    @ManyToOne
	@JoinColumn(name="tipotelefoneid")
	private Tipotelefone tipotelefone;

    public Telefone() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDd() {
		return this.dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Tipotelefone getTipotelefone() {
		return this.tipotelefone;
	}

	public void setTipotelefone(Tipotelefone tipotelefone) {
		this.tipotelefone = tipotelefone;
	}
	
}