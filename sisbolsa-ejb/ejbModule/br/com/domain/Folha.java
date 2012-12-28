package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the folha database table.
 * 
 */
@Entity
@Cacheable
public class Folha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

    @Temporal( TemporalType.DATE)
	@Column(name="data_deposito")
	private Date dataDeposito;

    @Temporal( TemporalType.DATE)
	@Column(name="data_folha")
	private Date dataFolha;

    @Temporal( TemporalType.DATE)
	private Date referencia;

	private boolean aberto;

	private String tipo;

	@Column(name="valor_total")
	private BigDecimal valorTotal;

	//bi-directional many-to-one association to Periodoletivo
    @ManyToOne
	@JoinColumn(name="periodoletivoid")
	private Periodoletivo periodoletivo;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="folha", cascade=CascadeType.ALL, fetch= FetchType.LAZY )
	private Set<Item> items;

    public Folha() {
    	this.aberto = true;
    	this.valorTotal = BigDecimal.ZERO;
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataDeposito() {
		return this.dataDeposito;
	}

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public Date getDataFolha() {
		return this.dataFolha;
	}

	public void setDataFolha(Date dataFolha) {
		this.dataFolha = dataFolha;
	}

	public Date getReferencia() {
		return this.referencia;
	}

	public void setReferencia(Date referencia) {
		this.referencia = referencia;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Periodoletivo getPeriodoletivo() {
		return this.periodoletivo;
	}

	public void setPeriodoletivo(Periodoletivo periodoletivo) {
		this.periodoletivo = periodoletivo;
	}
	
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
}