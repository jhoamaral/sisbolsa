package br.com.service;

import java.util.Calendar;

import javax.ejb.Stateless;

import br.com.domain.Periodoletivo;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QuerySingleResult;

@Stateless
public class ServicePeriodoletivo {
	
	public Periodoletivo getPeriodoletivoAtual() throws NoRecordFoundException{
		Calendar cal = Calendar.getInstance();
		int mes = cal.get(Calendar.MONTH);
		int ano = cal.get(Calendar.YEAR);
		int semestre = 1;
		if(mes>=5){
			semestre = 2;
		}	
		
		QuerySingleResult<Periodoletivo> query = QueryManager.PERIODOLETIVO.findByAnoSemestre()
															 .withAno(ano)
															 .withSemestre(semestre);
			
		return Repositorio.executeQuery(query);
	}
}
