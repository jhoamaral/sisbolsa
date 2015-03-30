package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Ativobolsa;
import br.com.repositorio.querybuilder.query.QuerySingleResult;


public class FindAtivobolsaWithCollections  extends QuerySingleResult<Ativobolsa>{
	
	private static final String QUERY = "Select a from Ativobolsa a " +
										"left join fetch a.matricula b "+
										"left join fetch b.pessoa c "+
										"left join fetch c.telefones d "+
										"left join fetch c.familiares f "+
										"where a = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Ativobolsa.class;
	}
	
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindAtivobolsaWithCollections withAtivobolsa(Ativobolsa ativobolsa){
		this.setParamters(1, ativobolsa);
		return this;
	}
}
