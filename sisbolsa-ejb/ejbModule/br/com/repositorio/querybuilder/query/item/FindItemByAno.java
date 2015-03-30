package br.com.repositorio.querybuilder.query.item;

import br.com.domain.Item;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindItemByAno  extends QueryListResult<Item>{
	
	
	private static final String QUERY = "Select o from Item o " +
										"LEFT JOIN o.folha a " +
										"where YEAR(a.referencia) = '${1}' " +
										"order by a.referencia, o.ativobolsa.matricula.pessoa.nome";
	
	@Override
	protected void setType() {
		this.type = Item.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindItemByAno withAno(String ano){
		this.setParamters(1, ano);
		return this;
	}


}
