package br.com.sisbolsa.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.domain.Usuario;
import br.com.repositorio.exceptions.LoginException;
import br.com.service.ServiceUsuario;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Usuario user;
	
	
	@EJB
	private ServiceUsuario serviceUsuario;
	
	
	public LoginController() {
		this.user = new Usuario();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public void autentica(){
		FacesContext context = FacesContext.getCurrentInstance();  
		try {
			this.user = serviceUsuario.autentica(this.user);
			context.getExternalContext().getSessionMap().remove("principalController");
			context.getExternalContext().redirect("principal.jsf");
		} catch (LoginException e) {	          
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!", e.getMessage()));  
		}catch (IOException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!", "Erro ao redirecionar usuário!"));  
		}
	}	
}
