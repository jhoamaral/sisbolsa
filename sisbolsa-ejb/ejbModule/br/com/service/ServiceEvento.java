package br.com.service;

import javax.ejb.Stateless;

import br.com.domain.Evento;
import br.com.domain.Historicovalor;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QuerySingleResult;

@Stateless
public class ServiceEvento {	
	
	public Historicovalor getUltimoHistoricoEnvento(Evento evento) throws NoRecordFoundException{
		QuerySingleResult<Historicovalor> query = QueryManager.EVENTO.findUltimoHistoricoEvento()
															  .withEvento(evento);
		return Repositorio.executeQuery(query);
	}
}
