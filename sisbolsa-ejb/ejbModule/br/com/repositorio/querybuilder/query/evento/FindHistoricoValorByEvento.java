package br.com.repositorio.querybuilder.query.evento;

import br.com.domain.Evento;
import br.com.domain.Historicovalor;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindHistoricoValorByEvento  extends QueryListResult<Historicovalor>{
	
	
	private static final String QUERY = "Select o from Historicovalor o " +
										"where eventoid = '${1}' "+
										"order by data desc";
	
	@Override
	protected void setType() {
		this.type = Historicovalor.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindHistoricoValorByEvento withEvento(Evento evento){
		this.setParamters(1, evento);
		return this;
	}


}
