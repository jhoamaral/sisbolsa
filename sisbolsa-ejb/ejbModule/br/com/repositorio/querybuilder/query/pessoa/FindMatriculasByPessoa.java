package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Matricula;
import br.com.domain.Pessoa;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindMatriculasByPessoa  extends QueryListResult<Matricula>{
	
	
	private static final String QUERY = "Select o from Matricula o " +
										"where pessoa = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Matricula.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindMatriculasByPessoa withPessoa(Pessoa pessoa){
		this.setParamters(1, pessoa);
		return this;
	}


}
