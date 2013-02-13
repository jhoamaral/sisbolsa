package br.com.repositorio.querybuilder.query.usuario;

import br.com.domain.Usuario;
import br.com.repositorio.querybuilder.query.QuerySingleResult;


public class FindByLogin  extends QuerySingleResult<Usuario>{
	
	private static final String QUERY = "Select o from Usuario o " +
										"where login = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Usuario.class;
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindByLogin withLogin(String login){
		this.setParamters(1, login);
		return this;
	}


}
