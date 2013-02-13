package br.com.repositorio.querybuilder.query.generic;

import br.com.repositorio.querybuilder.query.QueryListResult;


public class AllObjectsOrdered<T>  extends QueryListResult<T>{
	
	private Class<T> objectClass;
	
	private static final String QUERY = "Select o from ${name} o "+
										"order by ${1}";
	
	@Override
	protected void setType() {
		this.type = objectClass;
		
	}
	
	@Override
	public String getEjbQl() {		
		return 	QUERY.replace("${1}", String.valueOf(this.parameters.get(1)))
				 	 .replace("${name}", this.objectClass.getSimpleName());
	}
	
	public AllObjectsOrdered<T> setObject(Class<T> objectClass){
		this.objectClass = objectClass;
		return this;
	}
	
	public AllObjectsOrdered<T> withOrder(Object order){
		this.parameters.put(1, order);
		return this;
	}


}
