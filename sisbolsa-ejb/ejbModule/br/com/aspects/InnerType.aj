package br.com.aspects;

import br.com.domain.Boleto;
import br.com.domain.Endereco;
import br.com.domain.Evento;
import br.com.domain.Historicovalor;
import br.com.domain.Matriculaperiodo;
import br.com.domain.Pessoa;
import br.com.domain.Telefone;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.repositorio.querybuilder.query.QuerySingleResult;

public aspect InnerType {

	declare parents: br.com.domain.* extends br.com.generic.AbstractEntityDomain;


	public Historicovalor Evento.getUltimoHistoricovalor(){
		Historicovalor historicovalor = new Historicovalor();
		QuerySingleResult<Historicovalor> query = QueryManager.EVENTO.findUltimoHistoricoEvento()
															  .withEvento(this);
		try {
			historicovalor = Repositorio.executeQuery(query);
		} catch (NoRecordFoundException e) {}
		return historicovalor;
	}
	
	public Endereco Pessoa.getPrimeiroEndereco(){
		Endereco endereco = new Endereco();
		try {
			QuerySingleResult<Endereco> query = QueryManager.PESSOA.findPrimeiroEndereco()
															.withPessoa(this);
			endereco = Repositorio.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endereco;
	}
	
	public String Pessoa.getAllTelefones(){
		String retorno = "";
		try {
			QueryListResult<Telefone> query = QueryManager.PESSOA.findTelefonesByPessoa()
														  .withPessoa(this);
			for(Telefone tel:Repositorio.executeQuery(query)){
				String newTel ="("+ tel.getDd()+")";
				newTel += tel.getNumero();
				retorno += newTel+"/";		
			}
			retorno = retorno.substring(0, retorno.length()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return retorno;
	}
	
	public Boleto Matriculaperiodo.getUltimoBoleto(){
		Boleto boleto = new Boleto();
		try {
			QuerySingleResult<Boleto> query = QueryManager.BOLETO.findUltimoBoleto()
					  									  .withMatriculaperiodo(this);
			boleto = Repositorio.executeQuery(query);	
		} catch (Exception e) {}
		return boleto;
	}
}
