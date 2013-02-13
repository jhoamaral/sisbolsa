package br.com.service;

import javax.ejb.Stateless;

import br.com.domain.Matricula;
import br.com.domain.Socioeconeomico;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QuerySingleResult;

@Stateless
public class ServiceBolsista {
	
	public Socioeconeomico getUltimoSocioEconomico(Matricula matricula) throws NoRecordFoundException{
		QuerySingleResult<Socioeconeomico> query = QueryManager.BOLSISTA.findUltimoSocioEconomico()
															   .withMatricula(matricula);
		return Repositorio.executeQuery(query);
	}
}
