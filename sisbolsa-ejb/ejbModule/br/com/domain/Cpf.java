package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cpf database table.
 * 
 */
@Entity
@Cacheable
public class Cpf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

    @Temporal( TemporalType.DATE)
	@Column(name="data_expedicao")
	private Date dataExpedicao;

	private String numero;

	//bi-directional many-to-one association to Pessoa
    @OneToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;

    public Cpf() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataExpedicao() {
		return this.dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
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
	
}