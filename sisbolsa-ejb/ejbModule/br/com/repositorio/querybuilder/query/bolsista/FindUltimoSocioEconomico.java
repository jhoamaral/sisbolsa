package br.com.repositorio.querybuilder.query.bolsista;

import br.com.domain.Matricula;
import br.com.domain.Socioeconeomico;
import br.com.repositorio.querybuilder.query.QuerySingleResult;


public class FindUltimoSocioEconomico  extends QuerySingleResult<Socioeconeomico>{
	
	
	private static final String QUERY = "Select o from Socioeconeomico o " +
										"INNER JOIN o.matriculaperiodo b " +
										"INNER JOIN b.matricula c "+
										"where c = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Socioeconeomico.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindUltimoSocioEconomico withMatricula(Matricula matricula){
		this.setParamters(1, matricula);
		return this;
	}


}
