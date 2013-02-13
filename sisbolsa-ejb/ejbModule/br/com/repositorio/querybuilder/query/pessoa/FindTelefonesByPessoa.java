package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Pessoa;
import br.com.domain.Telefone;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindTelefonesByPessoa  extends QueryListResult<Telefone>{
	
	
	private static final String QUERY = "Select o from Telefone o " +
										"where pessoa = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Telefone.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindTelefonesByPessoa withPessoa(Pessoa pessoa){
		this.setParamters(1, pessoa);
		return this;
	}


}
