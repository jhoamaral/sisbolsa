package br.com.repositorio.querybuilder.query;

import java.util.HashMap;

public abstract class QueryAbstract<T>{
	
	protected HashMap<Integer, Object> parameters = new HashMap<Integer, Object>();
	protected Class<T> type;
	
	public void setParamters(Integer index, Object value) {
		this.parameters.put(index, value);
	}
	
	protected abstract void setType();
	
	public Class<T> getTypeObject(){
		return this.type;
	}
	
	public abstract String getEjbQl();
}
