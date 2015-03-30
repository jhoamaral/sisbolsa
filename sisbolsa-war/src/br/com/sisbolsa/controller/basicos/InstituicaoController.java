package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import br.com.domain.Curso;
import br.com.domain.Cursoinstituicao;
import br.com.domain.Instituicao;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.sisbolsa.controller.AbstractController;
import br.com.sisbolsa.converter.EntityConverter;

@ManagedBean
@ViewScoped
public class InstituicaoController extends AbstractController<Instituicao> implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	private List<SelectItem> comboBoxCurso = new ArrayList<SelectItem>();
	private Cursoinstituicao cursoSelecionado;
	

	public InstituicaoController() {
		super(2,"nome");
	}
	
	public void novo(){
		this.obj = new Instituicao();
		this.obj.setCursoinstituicaos(new HashSet<Cursoinstituicao>());
		this.cursoSelecionado = new Cursoinstituicao();
		this.setFiltro("");
	}

	
	@Override
	public void setObject(Instituicao obj) {
		this.obj = obj;
		
		QueryListResult<Cursoinstituicao> query = QueryManager.FACULDADE.findCursoInstituicaoByInstituicao()
															.withInstituicao(this.obj);
		Set<Cursoinstituicao> lista = new LinkedHashSet<Cursoinstituicao>(Repositorio.executeQuery(query));
		this.obj.setCursoinstituicaos(lista);
	}	
	
	public Cursoinstituicao getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Cursoinstituicao cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}
	
	public List<Cursoinstituicao> getCursos(){
		if(this.obj instanceof Instituicao){
			return new ArrayList<Cursoinstituicao>(this.obj.getCursoinstituicaos());
		}else{
			return new ArrayList<Cursoinstituicao>();
		}
		
	}

	public List<SelectItem> getComboBoxCurso(){
		if(this.comboBoxCurso.isEmpty()){
			QueryListResult<Curso> query = QueryManager.GENERIC.allObejctOrdered(Curso.class)
													   .withOrder("descricao");
			for(Curso curso:Repositorio.executeQuery(query)){
				this.comboBoxCurso.add(new SelectItem(curso,curso.getDescricao()));
			}
		}
		return this.comboBoxCurso;
	}
	
	protected String getFieldValue(Instituicao obj, int i) {
		switch (i) {
		case 0:
			return obj.getNome();
		case 1:
			return obj.getSigla();
		default:
			return "";
		}
	}
	
	public Converter getCursoConverter(){
		return EntityConverter.GetInstance(Curso.class);
	}
	
	public void addCurso(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			if(this.obj.getId() instanceof String){
				cursoSelecionado.setInstituicao(this.obj);
				Repositorio.cadastrar(cursoSelecionado);
				this.obj.getCursoinstituicaos().add(cursoSelecionado);
			}
		}catch (Exception e) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!","Ocorreu um erro ao salvar este registro! "+ e.getMessage()));
		}finally{
			this.cursoSelecionado = new Cursoinstituicao();
			
		}
	
	}
	
	public void removeCurso(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			Repositorio.delete(cursoSelecionado);
			this.obj.getCursoinstituicaos().remove(cursoSelecionado);
		}catch (Exception e) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!","Ocorreu um erro ao excluir este registro! "+ e.getMessage()));
		}finally{
			this.cursoSelecionado = new Cursoinstituicao();
			
		}
	}

}
