package br.com.repositorio.querybuilder.query.item;

import br.com.domain.Folha;
import br.com.domain.Item;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindItemByFolha  extends QueryListResult<Item>{
	
	
	private static final String QUERY = "Select o from Item o " +
										"where folha = '${1}' " +
										"order by matriculaperiodo.matricula.pessoa.nome";
	
	@Override
	protected void setType() {
		this.type = Item.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindItemByFolha withFolha(Folha folha){
		this.setParamters(1, folha);
		return this;
	}


}
