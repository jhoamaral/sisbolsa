package br.com.repositorio.querybuilder.query.faculdade;

import br.com.domain.Cursoinstituicao;
import br.com.domain.Instituicao;
import br.com.repositorio.querybuilder.query.QueryListResult;

public class FindCursoInstituicaoByInstituicao extends QueryListResult<Cursoinstituicao> {

	private static final String QUERY = "Select o from Cursoinstituicao o where " +
										"instituicao = '${1}' " +
										"order by curso.descricao";

	@Override
	protected void setType() {
		this.type = Cursoinstituicao.class;
	}

	@Override
	public String getEjbQl() {
		return QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
	}
	
	public FindCursoInstituicaoByInstituicao withInstituicao(Instituicao instituicao){
		this.parameters.put(1, instituicao);
		return this;
	}

}
