package br.com.repositorio.querybuilder.query.boleto;

import br.com.domain.Boleto;
import br.com.domain.Ativobolsa;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindBoletoByAtivobolsa  extends QueryListResult<Boleto>{
	
	
	private static final String QUERY = "Select o from Boleto o " +
										"where ativobolsa = '${1}' " +
										"order by data desc";
	
	@Override
	protected void setType() {
		this.type = Boleto.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindBoletoByAtivobolsa withAtivobolsa(Ativobolsa ativobolsa){
		this.setParamters(1, ativobolsa);
		return this;
	}


}
