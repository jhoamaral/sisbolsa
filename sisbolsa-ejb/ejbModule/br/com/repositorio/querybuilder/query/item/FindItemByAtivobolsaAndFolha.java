package br.com.repositorio.querybuilder.query.item;

import br.com.domain.Folha;
import br.com.domain.Item;
import br.com.domain.Ativobolsa;
import br.com.repositorio.querybuilder.query.QuerySingleResult;


public class FindItemByAtivobolsaAndFolha  extends QuerySingleResult<Item>{
	
	
	private static final String QUERY = "Select o from Item o " +
										"where ativobolsa = '${1}' and " +
										"folha = '${2}'" +
										"order by folha.referencia desc";
	
	@Override
	protected void setType() {
		this.type = Item.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)))
							 .replace("${2}", String.valueOf(this.parameters.get(2)));
		return result;
	}
	
	public FindItemByAtivobolsaAndFolha withAtivobolsa(Ativobolsa ativobolsa){
		this.setParamters(1, ativobolsa);
		return this;
	}
	
	public FindItemByAtivobolsaAndFolha withFolha(Folha folha){
		this.setParamters(2, folha);
		return this;
	}


}
