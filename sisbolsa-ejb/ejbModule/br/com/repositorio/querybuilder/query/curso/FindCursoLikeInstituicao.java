package br.com.repositorio.querybuilder.query.curso;

import br.com.domain.Curso;
import br.com.repositorio.querybuilder.query.QueryListResult;

public class FindCursoLikeInstituicao extends QueryListResult<Curso> {

	private static final String QUERY = "Select distinct o from Curso o " +
										"inner join o.cursoinstituicaos b " +
										"where b.instituicao like '${1}' " +
										"order by o.descricao";
	@Override
	protected void setType() {
		this.type = Curso.class;
	}

	@Override
	public String getEjbQl() {
		return QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
	}
	
	public FindCursoLikeInstituicao withInstituicao(String instituicao){
		this.parameters.put(1, instituicao);
		return this;
	}

}
