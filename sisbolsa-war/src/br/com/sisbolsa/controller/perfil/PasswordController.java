package br.com.sisbolsa.controller.perfil;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.domain.Usuario;
import br.com.repositorio.Repositorio;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class PasswordController extends AbstractController<Usuario> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;
	private String novoPassword;
	private String passwordDigitado;
	
	@ManagedProperty(value="#{loginController.user}") 
	private Usuario user;

	public PasswordController() {
		super( 0,"login");
	}

	@Override
	protected String getFieldValue(Usuario obj, int i) {
		return "";
	}

	@Override
	public void novo() {
		this.setNovoPassword(null);
		this.setPasswordDigitado(null);
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public String getNovoPassword() {
		return novoPassword;
	}

	public void setNovoPassword(String novoPassword) {
		this.novoPassword = novoPassword;
	}

	public String getPasswordDigitado() {
		return passwordDigitado;
	}

	public void setPasswordDigitado(String passwordDigitado) {
		this.passwordDigitado = passwordDigitado;
	}

	public void changePassword(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(this.getPasswordDigitado().equals(this.user.getSenha())){
			this.user.setSenha(this.getNovoPassword());
			try {
				Repositorio.editar(user);
				this.novo();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!", "Senha alterada com sucesso!"));
			} catch (Exception e) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!", e.getMessage()));
				e.printStackTrace();
				
			}
		}else{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!", "Senha incorreta!"));
		}
	}


	

}
