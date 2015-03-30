package br.com.repositorio.querybuilder.query.bolsista;

import br.com.domain.Matricula;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindMatriculaLikeCursoLikeInstituicaoLikePeriodo  extends QueryListResult<Matricula>{
	
	
	private static final String QUERY = "Select a from Matricula a " +
										"INNER JOIN a.ativobolsas b " +
										"where "+
										"a.cursoinstituicao.curso like '${1}' and " +
										"a.cursoinstituicao.instituicao like '${2}' and "+ 
										"b.periodoletivo like '${3}' " +
										"order by a.cursoinstituicao.instituicao.sigla, a.cursoinstituicao.curso.descricao, a.pessoa.nome";
	@Override
	protected void setType() {
		this.type = Matricula.class;
		
	}
	
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)))
							 .replace("${2}", String.valueOf(this.parameters.get(2)))
							 .replace("${3}", String.valueOf(this.parameters.get(3)));
		return result;
	}
	
	public FindMatriculaLikeCursoLikeInstituicaoLikePeriodo withCurso(String curso){
		this.setParamters(1, curso);
		return this;
	}
	
	public FindMatriculaLikeCursoLikeInstituicaoLikePeriodo withInstituicao(String instituicao){
		this.setParamters(2, instituicao);
		return this;
	}
	
	public FindMatriculaLikeCursoLikeInstituicaoLikePeriodo withPeriodo(String periodo){
		this.setParamters(3, periodo);
		return this;
	}


}
