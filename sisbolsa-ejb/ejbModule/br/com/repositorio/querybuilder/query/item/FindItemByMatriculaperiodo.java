package br.com.repositorio.querybuilder.query.item;

import br.com.domain.Item;
import br.com.domain.Matriculaperiodo;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindItemByMatriculaperiodo  extends QueryListResult<Item>{
	
	
	private static final String QUERY = "Select o from Item o " +
										"where matriculaperiodo = '${1}' " +
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
	
	public FindItemByMatriculaperiodo withMatriculaperiodo(Matriculaperiodo matricula){
		this.setParamters(1, matricula);
		return this;
	}


}
