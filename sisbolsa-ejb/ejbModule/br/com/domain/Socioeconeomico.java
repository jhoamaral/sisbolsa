package br.com.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * The persistent class for the socioeconeomico database table.
 * 
 */
@Entity
@Cacheable
public class Socioeconeomico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Boolean instalacoessanitarias;

	private BigDecimal renda;

	private Integer temporesidencia;

	//bi-directional many-to-one association to Condicoesmoradia
    @ManyToOne
	@JoinColumn(name="condicoesmoradiaid")
	private Condicoesmoradia condicoesmoradia;

	//bi-directional many-to-one association to Situacaojuridica
    @ManyToOne
	@JoinColumn(name="situacaojuridicaid")
	private Situacaojuridica situacaojuridica;

	//bi-directional many-to-one association to Situacaooperacional
    @ManyToOne
	@JoinColumn(name="situacaooperacionalid")
	private Situacaooperacional situacaooperacional;

    @OneToOne
	@JoinColumn(name="ativobolsaid")
	private Ativobolsa ativobolsa;

    public Socioeconeomico() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getInstalacoessanitarias() {
		return this.instalacoessanitarias;
	}

	public void setInstalacoessanitarias(Boolean instalacoessanitarias) {
		this.instalacoessanitarias = instalacoessanitarias;
	}

	public BigDecimal getRenda() {
		return this.renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public Integer getTemporesidencia() {
		return this.temporesidencia;
	}

	public void setTemporesidencia(Integer temporesidencia) {
		this.temporesidencia = temporesidencia;
	}

	public Condicoesmoradia getCondicoesmoradia() {
		return this.condicoesmoradia;
	}

	public void setCondicoesmoradia(Condicoesmoradia condicoesmoradia) {
		this.condicoesmoradia = condicoesmoradia;
	}
	
	public Situacaojuridica getSituacaojuridica() {
		return this.situacaojuridica;
	}

	public void setSituacaojuridica(Situacaojuridica situacaojuridica) {
		this.situacaojuridica = situacaojuridica;
	}
	
	public Situacaooperacional getSituacaooperacional() {
		return this.situacaooperacional;
	}

	public void setSituacaooperacional(Situacaooperacional situacaooperacional) {
		this.situacaooperacional = situacaooperacional;
	}

	public Ativobolsa getAtivobolsa() {
		return ativobolsa;
	}

	public void setAtivobolsa(Ativobolsa ativobolsa) {
		this.ativobolsa = ativobolsa;
	}

	
}