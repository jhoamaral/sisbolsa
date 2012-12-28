package br.com.sisbolsa.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.domain.Menu;
import br.com.domain.Usuario;
import br.com.service.ServiceMenu;

@ManagedBean
@SessionScoped
public class PrincipalController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pagina = "home.xhtml";
	private List<Menu> listaMenus;
	private Menu menu;
	
	@ManagedProperty(value="#{loginController.user}") 
	private Usuario user;
	
	@EJB
	private ServiceMenu serviceMenu;
	
	public PrincipalController() {
		this.menu = new Menu();
		this.menu.setLink("home.xhtml");
		this.menu.setLegenda("Seja Bem vindo!");
	}
	
	@PostConstruct
	public void initialize(){
		this.listaMenus = serviceMenu.getAllMenus();
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public List<Menu> getListaMenus() {
		return listaMenus;
	}
	
	public String getPagina(){
		return this.pagina;
	}
	
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	
	public Menu getMenu() {
		if(!this.isLogado()){
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				context.getExternalContext().redirect("index.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public boolean isLogado(){
		boolean retorno = false; 
		try {
			retorno = this.user.getId() instanceof String;
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}
	
	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.invalidate();
		try {
			context.getExternalContext().redirect("index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
