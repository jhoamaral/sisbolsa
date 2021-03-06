package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Ativobolsa;
import br.com.domain.Periodoletivo;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindAtivobolsaByPeriodoLetivo  extends QueryListResult<Ativobolsa>{
	
	
	private static final String QUERY = "Select a from Ativobolsa a " +
										"where a.periodoletivo = '${1}' " +
										"order by matricula.pessoa.nome";
	
	@Override
	protected void setType() {
		this.type = Ativobolsa.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindAtivobolsaByPeriodoLetivo withPeriodoLetivo(Periodoletivo periodoletivo){
		this.setParamters(1, periodoletivo);
		return this;
	}


}
