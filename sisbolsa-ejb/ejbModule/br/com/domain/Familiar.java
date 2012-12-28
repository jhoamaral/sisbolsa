package br.com.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the parente database table.
 * 
 */
@Entity
@Cacheable
public class Familiar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String nome;

	private BigDecimal renda;

	//bi-directional many-to-one association to Pessoa
    @ManyToOne
	@JoinColumn(name="pessoaid")
	private Pessoa pessoa;
    
    @Temporal( TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;
    
	//bi-directional many-to-one association to Tipoparentesco
    @ManyToOne
	@JoinColumn(name="tipoparentescoid")
	private Tipoparentesco tipoparentesco;

    public Familiar() {
    	this.renda = new BigDecimal(0);
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getRenda() {
		return this.renda.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Tipoparentesco getTipoparentesco() {
		return this.tipoparentesco;
	}

	public void setTipoparentesco(Tipoparentesco tipoparentesco) {
		this.tipoparentesco = tipoparentesco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}