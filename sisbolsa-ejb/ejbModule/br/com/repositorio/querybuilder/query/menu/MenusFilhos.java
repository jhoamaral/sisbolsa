package br.com.repositorio.querybuilder.query.menu;

import br.com.domain.Menu;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class MenusFilhos  extends QueryListResult<Menu>{
	
	
	private static final String QUERY = "Select o from Menu o " +
										"where menupai = '${1}'" +
										"order by ordem";
	
	@Override
	protected void setType() {
		this.type = Menu.class;
		
	}
	
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public QueryListResult<Menu> setMenuPai(Menu menu){
		this.setParamters(1, menu);
		return this;
	}

}
