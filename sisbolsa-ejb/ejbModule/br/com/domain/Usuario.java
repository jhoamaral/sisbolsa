package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Cacheable
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String login;

	private String nome;

	private String senha;

    public Usuario() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}