package br.com.service;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import br.com.domain.Periodoletivo;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;

@Stateless
public class ServicePeriodoletivo {

	private Repositorio<Periodoletivo> repositorioPeriodoletivo;
	
	@PostConstruct
	public void setObjClass(){
		this.repositorioPeriodoletivo = Repositorio.GetInstance(Periodoletivo.class);
	}
	
	public Periodoletivo getPeriodoletivoAtual() throws NoRecordFoundException{
		Calendar cal = Calendar.getInstance();
		int mes = cal.get(Calendar.MONTH);
		int ano = cal.get(Calendar.YEAR);
		int semestre = 1;
		if(mes>=5){
			semestre = 2;
		}	
		repositorioPeriodoletivo.addEquals("ano", ano);
		repositorioPeriodoletivo.addEquals("semestre", semestre);	
		return repositorioPeriodoletivo.getFirstRow();
	}
}
