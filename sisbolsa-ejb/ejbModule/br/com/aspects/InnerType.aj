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

public aspect InnerType {

	declare parents: br.com.domain.* extends br.com.generic.AbstractEntityDomain;


	public Historicovalor Evento.getUltimoHistoricovalor(){
		Historicovalor historicovalor = new Historicovalor();
		Repositorio<Historicovalor> repositorioHistoricovalor = Repositorio.GetInstance(Historicovalor.class);
		repositorioHistoricovalor.addEquals("eventoid",this);
		repositorioHistoricovalor.addOrder("data desc");
		try {
			historicovalor = repositorioHistoricovalor.getFirstRow();
		} catch (NoRecordFoundException e) {}
		return historicovalor;
	}
	
	public Endereco Pessoa.getPrimeiroEndereco(){
		Endereco endereco = new Endereco();
		try {
			Repositorio<Endereco> findEndereco = Repositorio.GetInstance(Endereco.class);
			findEndereco.addEquals("pessoa", this);
			endereco = findEndereco.getFirstRow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endereco;
	}
	
	public String Pessoa.getAllTelefones(){
		String retorno = "";
		try {
			Repositorio<Telefone> find= Repositorio.GetInstance(Telefone.class);
			find.addEquals("pessoa", this);
			for(Telefone tel:find.getAllList()){
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
			Repositorio<Boleto> repositorioBoleto = Repositorio.GetInstance(Boleto.class);
			repositorioBoleto.addEquals("matriculaperiodo",this);
			repositorioBoleto.addOrder("data desc");			
			boleto = repositorioBoleto.getFirstRow();	
		} catch (Exception e) {}
		return boleto;
	}
}
