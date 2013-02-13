package br.com.repositorio.querybuilder.query.evento;

import br.com.domain.Eventocauculado;
import br.com.domain.Item;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindEventoCalculadoByItem  extends QueryListResult<Eventocauculado>{
	
	
	private static final String QUERY = "Select o from Eventocauculado o " +
										"where item = '${1}' " +
										"order by descricao";
	
	@Override
	protected void setType() {
		this.type = Eventocauculado.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindEventoCalculadoByItem withItem(Item item){
		this.setParamters(1, item);
		return this;
	}


}
