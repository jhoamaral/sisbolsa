package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Pessoa;
import br.com.domain.Pessoaben;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindPessoaBensByPessoa  extends QueryListResult<Pessoaben>{
	
	
	private static final String QUERY = "Select o from Pessoaben o " +
										"where pessoa = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Pessoaben.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindPessoaBensByPessoa withPessoa(Pessoa pessoa){
		this.setParamters(1, pessoa);
		return this;
	}


}
