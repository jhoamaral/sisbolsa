package br.com.repositorio;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.generic.AbstractEntityDomain;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.repositorio.querybuilder.query.QuerySingleResult;
import br.com.service.UtilService;

public abstract class Repositorio {

	public static <T extends AbstractEntityDomain> PersistenceBean<T> persistenceBean(Class<T> objClass){
		PersistenceBean<T> persistenceBean = null;
		try {		
			persistenceBean = InitialContext.doLookup("java:app/sisbolsa-ejb/PersistenceBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return persistenceBean;
	}
	
	public static <T extends AbstractEntityDomain> void cadastrar(T obj) throws Exception{
		obj.setId(UtilService.generateOid());
		persistenceBean(obj.getClass()).cadastrar(obj);
	}

	public static <T extends AbstractEntityDomain> void editar(T obj) throws Exception{
		persistenceBean(obj.getClass()).editar(obj);
	}
	
	public static <T extends AbstractEntityDomain> void delete(T obj) throws Exception{
		persistenceBean(obj.getClass()).delete(obj.getClass(), obj);
	}

	@SuppressWarnings("unchecked")
	public static <T extends AbstractEntityDomain> T executeQuery(QuerySingleResult<T> query) throws NoRecordFoundException{	
		List<T> result = persistenceBean(query.getTypeObject()).getEntityManager().createQuery(query.getEjbQl()).getResultList();
		if(!result.isEmpty()){
			return (T) result.toArray()[0];
		}else{
			throw new NoRecordFoundException();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends AbstractEntityDomain> List<T> executeQuery(QueryListResult<T> query){	
		List<T> result = persistenceBean(query.getTypeObject()).getEntityManager().createQuery(query.getEjbQl()).getResultList();
		return result;
	}	

}
