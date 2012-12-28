package br.com.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import br.com.domain.Menu;
import br.com.repositorio.Repositorio;

@Stateless
public class ServiceMenu {

	private Repositorio<Menu> repositorioMenu;
	
	@PostConstruct
	public void setObjClass(){
		this.repositorioMenu = Repositorio.GetInstance(Menu.class);
	}
	
	public List<Menu> getAllMenus(){
		repositorioMenu.addWhere("menupai is null");
		repositorioMenu.addOrder("ordem");
		List<Menu> lista = repositorioMenu.getAllList();
		for(Menu menu:lista){
			repositorioMenu.clear();
			repositorioMenu.addEquals("menupai", menu);
			repositorioMenu.addOrder("ordem");
			menu.setMenus(repositorioMenu.getAllSet());
		}
		return lista;
	}
}
