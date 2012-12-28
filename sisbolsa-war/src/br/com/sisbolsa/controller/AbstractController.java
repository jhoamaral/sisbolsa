package br.com.sisbolsa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.repositorio.Repositorio;
import br.com.service.UtilService;
import br.com.sisbolsa.util.WebUtils;

public abstract class AbstractController<T> {

	private List<T> allObj = new ArrayList<T>();
	private String filtro;
	protected int fields;
	protected T obj;
	private String orderField;
	
	protected Repositorio<T> repositorio;


	public AbstractController() {
		
	}
	
	public AbstractController(Repositorio<T> repositorio, int fields, String orderField) {
		this.novo();
		this.fields = fields;
		this.repositorio= repositorio;
		this.setOrderField(orderField);
	}
	
	
	public abstract void novo();

	public T getObject() {
		return obj;
	}

	public void setObject(T obj) {
		this.obj = obj;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getOrderField() {
		return orderField;
	}


	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}


	public List<T> getAllObj() {
		return allObj;
	}


	public void setAllObj(List<T> allObj) {
		this.allObj = allObj;
	}


	public List<T> getObjects() {
		List<T> lista = new ArrayList<T>();
		if(this.getAllObj().isEmpty()){
			this.carregaAllObjects();
		}
		if(getFiltro().equals("")){
			lista = getAllObj();
		}else{
			for (T obj : getAllObj()) {
				if (isMatch(getFiltro(), obj)) {
					lista.add(obj);
				}
			}
		}
		return lista;
	}
	

	protected abstract String getFieldValue(T obj, int i);

	private boolean isMatch(String value, T obj) {
		String[] values = value.split(";");
		Set<String> total = new HashSet<String>();
		boolean found = false;
		try{
			for (String item : values) {
				boolean parar = false;
				int cont = 0;
				do {
					String val = String.valueOf(getFieldValue(obj, cont));
					if (val.toLowerCase().indexOf(item.toLowerCase()) > -1) {
						total.add(val);
						parar = true;
					} 
					cont++;
				} while (!parar && cont < this.fields);
			}
			if(total.size() == values.length){
				found = true;
			}
		}catch (Exception e) {}

		return found;
	}

	
	public void carregaAllObjects(){
		repositorio.addOrder(this.getOrderField());
		this.setAllObj(repositorio.getAllList());
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (UtilService.invocaMetodo(obj, "getId") instanceof String) {
				repositorio.editar(obj);
			} else {
				repositorio.cadastrar(obj);
				WebUtils.refreshComboBox(obj.getClass());
			}
			this.carregaAllObjects();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!","Registro salvo com sucesso! "));
		} catch (Exception e) {
			e.printStackTrace();
			this.novo();
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!","Ocorreu um erro ao salvar este registro! "+ e.getCause()));
		}
	}
	
	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			repositorio.delete(obj);
			WebUtils.refreshComboBox(obj.getClass());
			this.carregaAllObjects();
			this.novo();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!","Registro excluido com sucesso! "));
		} catch (Exception e) {
			e.printStackTrace();
			this.novo();
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!","Ocorreu um erro ao salvar este registro! "+ e.getCause()));
		}
	}

}
