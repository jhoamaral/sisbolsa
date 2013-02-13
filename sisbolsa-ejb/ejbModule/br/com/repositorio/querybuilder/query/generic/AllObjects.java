package br.com.repositorio.querybuilder.query.generic;

import br.com.repositorio.querybuilder.query.QueryListResult;

public class AllObjects<T> extends QueryListResult<T> {

	private Class<T> objectClass;

	private static final String QUERY = "Select o from ${name} o";

	@Override
	protected void setType() {
		this.type = objectClass;

	}

	@Override
	public String getEjbQl() {
		return QUERY.replace("${name}", this.objectClass.getSimpleName());
	}

	public AllObjects<T> setObject(Class<T> objectClass) {
		this.objectClass = objectClass;
		return this;
	}

}
