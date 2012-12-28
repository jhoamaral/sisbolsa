package br.com.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.service.UtilService;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@Cacheable
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

    @Temporal( TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;

	private String email;

	private String nome;
	
	private String pai;
	
	private String mae;
	
	private String observacao;
	
	private String agencia;
	
	private String conta;
	
	//bi-directional many-to-one association to Escolaridade
    @ManyToOne
	@JoinColumn(name="escolaridadeid")
	private Escolaridade escolaridade;

	//bi-directional many-to-one association to Estadocivil
    @ManyToOne
	@JoinColumn(name="estadocivilid")
	private Estadocivil estadocivil;

	//bi-directional many-to-one association to Cpf
    @OneToOne(mappedBy="pessoa", cascade=CascadeType.ALL)
	private Cpf cpf;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Endereco> enderecos;

	//bi-directional many-to-one association to Identidade
	@OneToOne(mappedBy="pessoa", cascade=CascadeType.ALL)
	private Identidade identidade;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Matricula> matriculas;

	//bi-directional many-to-one association to Parente
	@OneToMany(mappedBy="pessoa",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Familiar> familiares;

	//bi-directional many-to-one association to Pessoaben
	@OneToMany(mappedBy="pessoa",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Pessoaben> pessoabens;

	//bi-directional many-to-one association to Telefone
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Telefone> telefones;

	//bi-directional many-to-one association to Titulo
	@OneToOne(mappedBy="pessoa", cascade=CascadeType.ALL)
	private Titulo titulo;

    public Pessoa() {
    	this.titulo = new Titulo();
    	this.identidade = new Identidade();
    	this.cpf = new Cpf();
    	
    	this.titulo.setId(UtilService.generateOid());
    	this.identidade.setId(UtilService.generateOid());
    	this.cpf.setId(UtilService.generateOid());
    	
    	this.titulo.setPessoa(this);
    	this.identidade.setPessoa(this);
    	this.cpf.setPessoa(this);
    	
    	this.telefones = new HashSet<Telefone>();
    	this.enderecos = new HashSet<Endereco>();
    	this.familiares = new HashSet<Familiar>();
    	this.pessoabens = new HashSet<Pessoaben>();
    	this.matriculas = new HashSet<Matricula>();
    	
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Escolaridade getEscolaridade() {
		return this.escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	public Set<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public Set<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public Set<Familiar> getFamiliares() {
		return this.familiares;
	}

	public void setFamiliares(Set<Familiar> parentes) {
		this.familiares = parentes;
	}
	
	public Set<Telefone> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Identidade getIdentidade() {
		return identidade;
	}

	public void setIdentidade(Identidade identidade) {
		this.identidade = identidade;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public Estadocivil getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(Estadocivil estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public Set<Pessoaben> getPessoabens() {
		return pessoabens;
	}

	public void setPessoabens(Set<Pessoaben> pessoabens) {
		this.pessoabens = pessoabens;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}
	
}