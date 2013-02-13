package br.com.repositorio.querybuilder.query.menu;

import br.com.domain.Menu;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class MenusPrincipais  extends QueryListResult<Menu>{
	
	
	private static final String QUERY = "Select o from Menu o " +
										"where menupai is null " +
										"order by ordem";
	
	@Override
	protected void setType() {
		this.type = Menu.class;
		
	}
	
	@Override
	public String getEjbQl() {
		return QUERY;
	}

}
