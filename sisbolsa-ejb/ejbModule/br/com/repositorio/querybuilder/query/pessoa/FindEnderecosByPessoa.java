package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Endereco;
import br.com.domain.Pessoa;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindEnderecosByPessoa  extends QueryListResult<Endereco>{
	
	
	private static final String QUERY = "Select o from Endereco o " +
										"where pessoa = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Endereco.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindEnderecosByPessoa withPessoa(Pessoa pessoa){
		this.setParamters(1, pessoa);
		return this;
	}


}
