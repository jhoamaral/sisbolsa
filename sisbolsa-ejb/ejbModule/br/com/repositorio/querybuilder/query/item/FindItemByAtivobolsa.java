package br.com.repositorio.querybuilder.query.item;

import br.com.domain.Item;
import br.com.domain.Ativobolsa;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindItemByAtivobolsa  extends QueryListResult<Item>{
	
	
	private static final String QUERY = "Select o from Item o " +
										"where ativobolsa = '${1}' " +
										"order by folha.referencia desc";
	
	@Override
	protected void setType() {
		this.type = Item.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindItemByAtivobolsa withAtivobolsa(Ativobolsa ativobolsa){
		this.setParamters(1, ativobolsa);
		return this;
	}


}
