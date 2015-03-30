package br.com.repositorio.querybuilder.query.boleto;

import java.util.Date;

import br.com.domain.Boleto;
import br.com.domain.Ativobolsa;
import br.com.repositorio.querybuilder.query.QuerySingleResult;


public class FindBoletoByData  extends QuerySingleResult<Boleto>{
	
	
	private static final String QUERY = "Select o from Boleto o " +
										"where ativobolsa = '${1}' and " +
										"data = '${2}'";
	
	@Override
	protected void setType() {
		this.type = Boleto.class;
		
	}
	@Override
	public String getEjbQl() {
		return QUERY.replace("${1}", String.valueOf(this.parameters.get(1)))
					.replace("${2}", String.valueOf(this.parameters.get(2)));
	}
	
	public FindBoletoByData withAtivobolsa(Ativobolsa ativobolsa){
		this.setParamters(1, ativobolsa);
		return this;
	}
	
	public FindBoletoByData withData(Date data){
		this.setParamters(2, data);
		return this;
	}


}
