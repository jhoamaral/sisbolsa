package br.com.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.domain.Endereco;
import br.com.domain.Familiar;
import br.com.domain.Pessoa;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QuerySingleResult;

public class PessoaEndereco{
	
	private Pessoa pessoa;
	private Familiar parente;
	private Endereco endereco;
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Familiar getParente() {
		return parente;
	}
	
	public void setParente(Familiar parente) {
		this.parente = parente;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public static PessoaEndereco getObj(Familiar parente){
		PessoaEndereco obj = new PessoaEndereco();
		obj.setParente(parente);
		obj.setPessoa(parente.getPessoa());	
		Endereco endereco = new Endereco();
		QuerySingleResult<Endereco> query = QueryManager.PESSOA.findPrimeiroEndereco()
														.withPessoa(parente.getPessoa());
		try {
			endereco = Repositorio.executeQuery(query);
		} catch (NoRecordFoundException e) {}
		obj.setEndereco(endereco);	
		return obj;
	}
	
	public static List<PessoaEndereco> getObj(List<Familiar> parentList){
		List<PessoaEndereco> lista = new ArrayList<PessoaEndereco>();
		for(Familiar parente:parentList){
			lista.add(getObj(parente));
		}
		return lista;
	}
	
}
