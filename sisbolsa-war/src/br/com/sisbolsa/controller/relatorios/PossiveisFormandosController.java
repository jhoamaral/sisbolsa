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
import br.com.domain.Instituicao;
import br.com.domain.Matricula;
import br.com.domain.Periodoletivo;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.service.ReportService;
import br.com.service.UtilService;
import br.com.service.reports.ReportModel;
import br.com.sisbolsa.util.Constantes;

@ManagedBean
@ViewScoped
public class PossiveisFormandosController  implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;
	private String instituicaoSelecionado;
	private String cursoSelecionado;
	private Periodoletivo periodoletivoSelecionado;
	
	@EJB
	ReportService reportService;

	public String getInstituicaoSelecionado() {
		return instituicaoSelecionado;
	}

	public void setInstituicaoSelecionado(String instituicaoSelecionado) {
		this.instituicaoSelecionado = instituicaoSelecionado;
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
	
	public List<SelectItem> getListaInstituicao() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		QueryListResult<Instituicao> query = QueryManager.GENERIC.allObejctOrdered(Instituicao.class)
				   									   .withOrder("nome");
		for (Instituicao obj : Repositorio.executeQuery(query)) {
			lista.add(new SelectItem(obj.getId(), String.valueOf(obj.getNome())));
		}
		
		return lista;
	}

	public List<SelectItem> getListaCursoInstituicao() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		QueryListResult<Curso> query = QueryManager.CURSO.findCursoLikeInstituicao()
				   								   .withInstituicao(this.instituicaoSelecionado);
		for (Curso obj : Repositorio.executeQuery(query)) {
			lista.add(new SelectItem(obj.getId(), obj.getDescricao()));
		}

		return lista;
	}

	public DefaultStreamedContent imprimir(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		QueryListResult<Matricula> query = QueryManager.BOLSISTA.findMatriculaLikeCursoLikeInstituicaoLikePeriodo()
													   .withCurso(this.cursoSelecionado)
													   .withInstituicao(this.instituicaoSelecionado)
													   .withPeriodo(this.periodoletivoSelecionado.getId());
		
		HashSet<Matricula> result = new LinkedHashSet<Matricula>();
		for(Matricula matricula:Repositorio.executeQuery(query)){
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
