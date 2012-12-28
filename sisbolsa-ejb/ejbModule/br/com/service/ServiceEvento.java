package br.com.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import br.com.domain.Evento;
import br.com.domain.Historicovalor;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;

@Stateless
public class ServiceEvento {

	private Repositorio<Historicovalor> repositorioHistoricovalor;
	
	@PostConstruct
	public void setObjClass(){
		this.repositorioHistoricovalor = Repositorio.GetInstance(Historicovalor.class);
	}
	
	
	public Historicovalor getUltimoHistoricoEnvento(Evento evento) throws NoRecordFoundException{
		repositorioHistoricovalor.addEquals("eventoid",evento);
		repositorioHistoricovalor.addOrder("data desc");
		return repositorioHistoricovalor.getFirstRow();
	}
}
