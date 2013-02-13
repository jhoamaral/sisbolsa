package br.com.repositorio.querybuilder.query.curso;

import br.com.domain.Curso;
import br.com.repositorio.querybuilder.query.QueryListResult;

public class FindCursoLikeFaculdade extends QueryListResult<Curso> {

	private static final String QUERY = "Select distinct o from Curso o " +
										"inner join o.cursofaculdades b " +
										"where b.faculdade like '${1}' " +
										"order by o.descricao";
	@Override
	protected void setType() {
		this.type = Curso.class;
	}

	@Override
	public String getEjbQl() {
		return QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
	}
	
	public FindCursoLikeFaculdade withFaculdade(String faculdade){
		this.parameters.put(1, faculdade);
		return this;
	}

}
