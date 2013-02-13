package br.com.service;

import javax.ejb.Stateless;

import br.com.domain.Usuario;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.LoginException;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QuerySingleResult;

@Stateless
public class ServiceUsuario {
	
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
		QuerySingleResult<Usuario> query = QueryManager.USUARIO.findByLogin()
													   .withLogin(usuario.getLogin().toLowerCase());
		return Repositorio.executeQuery(query);
	}
}
