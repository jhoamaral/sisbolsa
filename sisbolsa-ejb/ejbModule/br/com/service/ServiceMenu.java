package br.com.service;

import java.util.HashSet;
import java.util.List;

import javax.ejb.Stateless;

import br.com.domain.Menu;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;

@Stateless
public class ServiceMenu {
	
	public List<Menu> getAllMenus(){
		
		QueryListResult<Menu> query = QueryManager.MENU.menusPrincipais();
		List<Menu> lista = Repositorio.executeQuery(query);
		
		for(Menu menu:lista){
			QueryListResult<Menu> queryFilhos = QueryManager.MENU.menusFilhos()
															.setMenuPai(menu);
			HashSet<Menu> filhos = new HashSet<Menu>(Repositorio.executeQuery(queryFilhos));
			menu.setMenus(filhos);
		}
		
		return lista;
	}
}
