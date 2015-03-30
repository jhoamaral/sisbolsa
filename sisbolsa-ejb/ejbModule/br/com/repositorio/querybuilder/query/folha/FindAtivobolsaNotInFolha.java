package br.com.repositorio.querybuilder.query.folha;

import br.com.domain.Folha;
import br.com.domain.Ativobolsa;
import br.com.repositorio.querybuilder.query.QueryListResult;

public class FindAtivobolsaNotInFolha  extends QueryListResult<Ativobolsa>{
	
	
	private static final String QUERY = "select o from Ativobolsa o where o not in("+
										"select a from Ativobolsa a "+
										"inner join a.items b "+
										"inner join b.folha c "+
										"where c = '${1}' "+
										") and o.periodoletivo = '${2}' " +
										"order by o.matricula.cursoinstituicao.instituicao.sigla asc, o.matricula.pessoa.nome asc";
	
	@Override
	protected void setType() {
		this.type = Ativobolsa.class;
		
	}
	@Override
	public String getEjbQl() {
		return QUERY.replace("${1}", String.valueOf(this.parameters.get(1)))
					.replace("${2}", String.valueOf(this.parameters.get(2)));
	}

	
	public FindAtivobolsaNotInFolha withFolha(Folha folha){
		this.setParamters(1, folha);
		this.setParamters(2, folha.getPeriodoletivo());
		return this;
	}
}
