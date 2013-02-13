package br.com.repositorio.querybuilder.query.folha;

import br.com.domain.Matriculaperiodo;
import br.com.domain.Periodoletivo;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindMatriculaPeriodoByPeriodoLetivoOrderedByCurso  extends QueryListResult<Matriculaperiodo>{
	
	
	private static final String QUERY = "Select a from Matriculaperiodo a " +
										"where a.periodoletivo = '${1}' " +
										"order by matricula.cursofaculdade.faculdade.sigla asc,matricula.pessoa.nome";
	
	@Override
	protected void setType() {
		this.type = Matriculaperiodo.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindMatriculaPeriodoByPeriodoLetivoOrderedByCurso withPeriodoLetivo(Periodoletivo periodoletivo){
		this.setParamters(1, periodoletivo);
		return this;
	}


}
