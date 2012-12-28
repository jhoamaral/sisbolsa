package br.com.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import br.com.domain.Usuario;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.LoginException;
import br.com.repositorio.exceptions.NoRecordFoundException;

@Stateless
public class ServiceUsuario {
	
	private Repositorio<Usuario> repositorioUsuario;
	
	@PostConstruct
	public void setObjClass(){
		this.repositorioUsuario = Repositorio.GetInstance(Usuario.class);
	}
	
    public Usuario autentica(Usuario login) throws LoginException{
    	Usuario user = null;
    	try{
    		if(!(login.getLogin() instanceof String)){
    			throw new LoginException("Informe seu Login!");
    		}
    		if(!(login.getSenha() instanceof String)){
    			throw new LoginException("Informe sua senha!");
    		}
    	
    		user = this.findByLogin(login);
    		
    		if(!(user.getSenha().equals(login.getSenha()))){
    			throw new LoginException("Senha incorreta!");
    		}
    		
    	}catch (NoRecordFoundException e) {
    		throw new LoginException("Usuário não encontrado!");
		}
    	return user;
    }
    
    public Usuario findByLogin(Usuario usuario) throws NoRecordFoundException{
		Usuario user = null;
		repositorioUsuario.addEquals("login", usuario.getLogin().toLowerCase());
		user = repositorioUsuario.getFirstRow();
		return user;
	}



}
