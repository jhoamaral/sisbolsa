package br.com.sisbolsa.controller.relatorios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultStreamedContent;

import br.com.domain.Curso;
import br.com.domain.Faculdade;
import br.com.domain.Matricula;
import br.com.domain.Periodoletivo;
import br.com.repositorio.Repositorio;
import br.com.service.ReportService;
import br.com.service.UtilService;
import br.com.service.reports.ReportModel;
import br.com.sisbolsa.util.Constantes;

@ManagedBean
@ViewScoped
public class PossiveisFormandosController  implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;
	private String faculdadeSelecionado;
	private String cursoSelecionado;
	private Periodoletivo periodoletivoSelecionado;
	
	@EJB
	ReportService reportService;

	public String getFaculdadeSelecionado() {
		return faculdadeSelecionado;
	}

	public void setFaculdadeSelecionado(String faculdadeSelecionado) {
		this.faculdadeSelecionado = faculdadeSelecionado;
	}

	public String getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(String cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public Periodoletivo getPeriodoletivoSelecionado() {
		return periodoletivoSelecionado;
	}

	public void setPeriodoletivoSelecionado(Periodoletivo periodoletivoSelecionado) {
		this.periodoletivoSelecionado = periodoletivoSelecionado;
	}

	public PossiveisFormandosController() {
	
	}
	
	public List<SelectItem> getListaFaculdade() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		Repositorio<Faculdade> getObj = Repositorio.GetInstance(Faculdade.class);
		getObj.addOrder("nome");
		for (Faculdade obj : getObj.getAllList()) {
			lista.add(new SelectItem(obj.getId(), String.valueOf(obj.getNome())));
		}
		
		return lista;
	}

	public List<SelectItem> getListaCursoFaculdade() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		Repositorio<Curso> getObj = Repositorio.GetInstance(Curso.class);
		getObj.setSelect("distinct a");
		getObj.setAlias("a");
		getObj.join("inner join a.cursofaculdades b");
		getObj.addWhere("b.faculdade like '"+ this.getFaculdadeSelecionado()+"'");
		getObj.addOrder("a.descricao");
		for (Curso obj : getObj.getAllList()) {
			lista.add(new SelectItem(obj.getId(), obj.getDescricao()));
		}

		return lista;
	}

	public DefaultStreamedContent imprimir(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		Repositorio<Matricula> getMatriculas = Repositorio.GetInstance(Matricula.class);
		getMatriculas.setAlias("a");
		getMatriculas.join("inner join a.matriculaperiodos b");
		getMatriculas.addWhere("b.periodoletivo like '"+ this.periodoletivoSelecionado+"' ");
		getMatriculas.addWhere("a.cursofaculdade.curso like '"+ this.cursoSelecionado+"' ");
		getMatriculas.addWhere("a.cursofaculdade.faculdade like '"+ this.faculdadeSelecionado+"' ");
		getMatriculas.addOrder("a.cursofaculdade.faculdade.sigla, a.cursofaculdade.curso.descricao, a.pessoa.nome");
		
		HashSet<Matricula> result = new LinkedHashSet<Matricula>();
		for(Matricula matricula:getMatriculas.getAllSet()){
			if(UtilService.calculaPeriodoAtual(matricula.getPeriodoletivo(),this.periodoletivoSelecionado) >= matricula.getDuracaoCurso()){
				result.add(matricula);
			}
		}
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("logo", contexto.getExternalContext().getRealPath(Constantes.LOGO));
		params.put("semestre",this.periodoletivoSelecionado.getAno()+"/"+this.periodoletivoSelecionado.getSemestre());
		try {
			return new DefaultStreamedContent(reportService.gerar(ReportModel.POSSIVEIS_FORMANDOS,params,result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}

}
