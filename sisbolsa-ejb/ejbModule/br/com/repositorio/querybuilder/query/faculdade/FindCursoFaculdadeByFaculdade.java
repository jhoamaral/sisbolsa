package br.com.repositorio.querybuilder.query.faculdade;

import br.com.domain.Cursofaculdade;
import br.com.domain.Faculdade;
import br.com.repositorio.querybuilder.query.QueryListResult;

public class FindCursoFaculdadeByFaculdade extends QueryListResult<Cursofaculdade> {

	private static final String QUERY = "Select o from Cursofaculdade o where " +
										"faculdade = '${1}' " +
										"order by curso.descricao";

	@Override
	protected void setType() {
		this.type = Cursofaculdade.class;
	}

	@Override
	public String getEjbQl() {
		return QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
	}
	
	public FindCursoFaculdadeByFaculdade withFaculdade(Faculdade faculdade){
		this.parameters.put(1, faculdade);
		return this;
	}

}
