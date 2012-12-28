package br.com.service;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;

import br.com.domain.Boleto;
import br.com.domain.Evento;
import br.com.domain.Historicovalor;
import br.com.domain.Matriculaperiodo;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;

@Stateless
public class ServiceBoleto {
	
	private Repositorio<Boleto> repositorioBoleto;
	
	@EJB
	private ServiceEvento serviceEvento;
	
	
	@PostConstruct
	public void setObjClass(){
		this.repositorioBoleto = Repositorio.GetInstance(Boleto.class);
	}
	
	public Boleto getUltimoBoleto(Matriculaperiodo matricula) throws NoRecordFoundException{
		repositorioBoleto.addEquals("matriculaperiodo",matricula);
		repositorioBoleto.addOrder("data desc");
		return repositorioBoleto.getFirstRow();	
	}
	
	public BigDecimal calculaEvento(Evento evento,BigDecimal valorBoleto) throws NoRecordFoundException{
		Historicovalor valorEvento = serviceEvento.getUltimoHistoricoEnvento(evento);			
		if(valorEvento.getPercentual() == 0 && valorEvento.getValor().floatValue() == 0){
			return BigDecimal.ZERO;
		}else{
			BigDecimal porcentagem = new BigDecimal(valorEvento.getPercentual());
			BigDecimal valor = valorEvento.getValor();			
			BigDecimal valortotal = valor.add(valorBoleto.multiply(porcentagem.divide(new BigDecimal(100)))).setScale(2, BigDecimal.ROUND_HALF_EVEN);			
			return valortotal;
		}		
	}
	
	public  Boleto getBoletoAnterior(Boleto boleto) throws NoRecordFoundException{
		Calendar calendario = Calendar.getInstance();
		calendario.setTimeInMillis(boleto.getData().getTime());
		
		try {
			calendario.set(Calendar.MONTH, calendario.get(Calendar.MONTH)-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new EntityNotFoundException("Não Existe boleto anterior!");
		}

		repositorioBoleto.addEquals("matriculaperiodo",boleto.getMatriculaperiodo());
		repositorioBoleto.addEquals("data", calendario.getTime());
		return repositorioBoleto.getFirstRow();	
	}
	
	public BigDecimal calculaDiferenca(Evento evento, Boleto boletoAtual) throws NoRecordFoundException{
		try {
			Boleto boletoAnterior = this.getBoletoAnterior(boletoAtual);
			BigDecimal valorAtual = this.calculaEvento(evento, boletoAtual.getValor());
			BigDecimal valorAnterior = this.calculaEvento(evento, boletoAnterior.getValor());
			if(!valorAtual.equals(valorAnterior)){
				return valorAtual.subtract(valorAnterior);
			}
		} catch (Exception e) {}
		
		return BigDecimal.ZERO;	
	}
	
	
	
}
