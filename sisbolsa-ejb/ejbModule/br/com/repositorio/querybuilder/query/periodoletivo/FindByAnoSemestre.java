package br.com.repositorio.querybuilder.query.periodoletivo;

import br.com.domain.Periodoletivo;
import br.com.repositorio.querybuilder.query.QuerySingleResult;


public class FindByAnoSemestre  extends QuerySingleResult<Periodoletivo>{
	
	
	private static final String QUERY = "Select o from Periodoletivo o " +
										"where ano = '${1}' and "+
										"semestre = '${2}'";
	
	@Override
	protected void setType() {
		this.type = Periodoletivo.class;
		
	}
	
	@Override
	public String getEjbQl() {
		return QUERY.replace("${1}", String.valueOf(this.parameters.get(1)))
					.replace("${2}", String.valueOf(this.parameters.get(2)));
	}
	
	public FindByAnoSemestre withAno(int ano){
		this.setParamters(1, ano);
		return this;
	}
	
	public FindByAnoSemestre withSemestre(int semestre){
		this.setParamters(2, semestre);
		return this;
	}


}
