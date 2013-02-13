package br.com.repositorio.querybuilder.query.generic;

import br.com.repositorio.querybuilder.query.QuerySingleResult;

public class FindById<T> extends QuerySingleResult<T> {

	private Class<T> objectClass;

	private static final String QUERY = "Select o from ${name} o " +
										"where id = '${1}'";

	@Override
	protected void setType() {
		this.type = objectClass;

	}

	@Override
	public String getEjbQl() {
		return QUERY.replace("${name}", this.objectClass.getSimpleName())
					.replace("${1}", String.valueOf(this.parameters.get(1)));
	}

	public FindById<T> setObject(Class<T> objectClass) {
		this.objectClass = objectClass;
		return this;
	}
	
	public FindById<T> withId(String id) {
		this.setParamters(1, id);
		return this;
	}

}
