package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import br.com.domain.Curso;
import br.com.domain.Cursofaculdade;
import br.com.domain.Faculdade;
import br.com.repositorio.Repositorio;
import br.com.sisbolsa.controller.AbstractController;
import br.com.sisbolsa.converter.EntityConverter;

@ManagedBean
@ViewScoped
public class FaculdadeController extends AbstractController<Faculdade> implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	private List<SelectItem> comboBoxCurso = new ArrayList<SelectItem>();
	private Cursofaculdade cursoSelecionado;
	

	public FaculdadeController() {
		super(Repositorio.GetInstance(Faculdade.class), 2,"nome");
	}
	
	public void novo(){
		this.obj = new Faculdade();
		this.obj.setCursofaculdades(new HashSet<Cursofaculdade>());
		this.cursoSelecionado = new Cursofaculdade();
		this.setFiltro("");
	}

	
	@Override
	public void setObject(Faculdade obj) {
		this.obj = obj;
		
		Repositorio<Cursofaculdade> cursos = Repositorio.GetInstance(Cursofaculdade.class);
		cursos.addEquals("faculdade", this.obj);
		this.obj.setCursofaculdades(cursos.getAllSet());
	}	
	
	public Cursofaculdade getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Cursofaculdade cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}
	
	public List<Cursofaculdade> getCursos(){
		if(this.obj instanceof Faculdade){
			return new ArrayList<Cursofaculdade>(this.obj.getCursofaculdades());
		}else{
			return new ArrayList<Cursofaculdade>();
		}
		
	}

	public List<SelectItem> getComboBoxCurso(){
		if(this.comboBoxCurso.isEmpty()){
			Repositorio<Curso> getCurso = Repositorio.GetInstance(Curso.class);
			getCurso.addOrder("descricao");
			for(Curso curso:getCurso.getAllList()){
				this.comboBoxCurso.add(new SelectItem(curso,curso.getDescricao()));
			}
		}
		return this.comboBoxCurso;
	}
	
	protected String getFieldValue(Faculdade obj, int i) {
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
				Repositorio<Cursofaculdade> curso = Repositorio.GetInstance(Cursofaculdade.class);
				cursoSelecionado.setFaculdade(this.obj);
				curso.cadastrar(cursoSelecionado);
				this.obj.getCursofaculdades().add(cursoSelecionado);
			}
		}catch (Exception e) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!","Ocorreu um erro ao salvar este registro! "+ e.getMessage()));
		}finally{
			this.cursoSelecionado = new Cursofaculdade();
			
		}
	
	}
	
	public void removeCurso(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			
			Repositorio<Cursofaculdade> curso = Repositorio.GetInstance(Cursofaculdade.class);
			curso.delete(cursoSelecionado);
			this.obj.getCursofaculdades().remove(cursoSelecionado);
		}catch (Exception e) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!","Ocorreu um erro ao excluir este registro! "+ e.getMessage()));
		}finally{
			this.cursoSelecionado = new Cursofaculdade();
			
		}
	}

}
