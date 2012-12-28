package br.com.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import br.com.domain.Matricula;
import br.com.domain.Socioeconeomico;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;

@Stateless
public class ServiceBolsista {
	
	private Repositorio<Socioeconeomico> repositorioSocioeconomico;
	
	@PostConstruct
	public void setObjClass(){
		this.repositorioSocioeconomico = Repositorio.GetInstance(Socioeconeomico.class);
	}
	
	public Socioeconeomico getUltimoSocioEconomico(Matricula matricula) throws NoRecordFoundException{
		repositorioSocioeconomico.setAlias("a");
		repositorioSocioeconomico.join("INNER JOIN a.matriculaperiodo b");
		repositorioSocioeconomico.join("INNER JOIN b.matricula c");
		repositorioSocioeconomico.addEquals("c", matricula );
		return repositorioSocioeconomico.getFirstRow();
	}
}
