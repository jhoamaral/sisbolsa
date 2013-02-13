package br.com.repositorio.querybuilder.query.pessoa;

import br.com.domain.Matriculaperiodo;
import br.com.repositorio.querybuilder.query.QuerySingleResult;


public class FindMatriculaPeriodoWithCollections  extends QuerySingleResult<Matriculaperiodo>{
	
	private static final String QUERY = "Select a from Matriculaperiodo a " +
										"left join fetch a.matricula b "+
										"left join fetch b.pessoa c "+
										"left join fetch c.telefones d "+
										"left join fetch c.familiares f "+
										"where a = '${1}'";
	
	@Override
	protected void setType() {
		this.type = Matriculaperiodo.class;
	}
	
	@Override
	public String getEjbQl() {
		String result = QUERY.replace("${1}", String.valueOf(this.parameters.get(1)));
		return result;
	}
	
	public FindMatriculaPeriodoWithCollections withMatriculaperiodo(Matriculaperiodo matriculaperiodo){
		this.setParamters(1, matriculaperiodo);
		return this;
	}
}
