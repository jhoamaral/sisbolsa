package br.com.repositorio.querybuilder.query.folha;

import br.com.domain.Folha;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindFolhasAbertas  extends QueryListResult<Folha>{
	
	
	private static final String QUERY = "Select o from Folha o " +
										"where aberto = true " +
										"order by dataFolha";
	
	@Override
	protected void setType() {
		this.type = Folha.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}

}
