package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Familiar;
import br.com.domain.Pessoa;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindFamiliaresByPessoa  extends QueryListResult<Familiar>{
	
	
	private static final String QUERY = "Select o from Familiar o " +
										"where pessoa = '${1}' " +
										"order by nome";
	
	@Override
	protected void setType() {
		this.type = Familiar.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindFamiliaresByPessoa withPessoa(Pessoa pessoa){
		this.setParamters(1, pessoa);
		return this;
	}


}
