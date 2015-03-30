package br.com.repositorio.querybuilder.query.item;

import br.com.domain.Folha;
import br.com.domain.Item;
import br.com.repositorio.querybuilder.query.QueryListResult;


public class FindItensWithCollectionsByFolha  extends QueryListResult<Item>{
	
	
	private static final String QUERY = "Select a from Item a " +
										"LEFT join FETCH a.eventocauculados b " +
										"LEFT join FETCH a.ativobolsa c " +
										"LEFT join FETCH c.matricula d " +
										"LEFT join FETCH d.pessoa e " +
										"where a.folha = '${1}' " +
										"order by d.cursoinstituicao.instituicao.sigla asc,e.nome asc";
	@Override
	protected void setType() {
		this.type = Item.class;
		
	}
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindItensWithCollectionsByFolha withFolha(Folha folha){
		this.setParamters(1, folha);
		return this;
	}

}
