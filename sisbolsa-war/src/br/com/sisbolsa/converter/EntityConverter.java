package br.com.sisbolsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.persistence.EntityManager;

import br.com.generic.AbstractEntityDomain;
import br.com.repositorio.Repositorio;

public class EntityConverter<E extends AbstractEntityDomain> implements Converter {
	private EntityManager em;
	private Class<E> type;
	
	public EntityConverter(){
		this.em = null;
		this.type = null;
	}

	public EntityConverter(Class<E> type) {
		this.em = Repositorio.persistenceBean(type).getEntityManager();
		this.type = type;
	}
	
	public static <T extends AbstractEntityDomain> EntityConverter<T> GetInstance(Class<T> objClass) {
		EntityConverter<T> converter = new EntityConverter<T>(objClass);
		return converter;
	}

	public Object getAsObject(FacesContext face, UIComponent c, String id) throws ConverterException {
		Object retorno = this.em.find(this.type, id);
		
		return retorno;
	}

	public String getAsString(FacesContext face, UIComponent c, Object obj) throws ConverterException {
		return String.valueOf(obj);
	}
	
}