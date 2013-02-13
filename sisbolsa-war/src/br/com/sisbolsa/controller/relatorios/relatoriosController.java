package br.com.sisbolsa.controller.relatorios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import br.com.domain.Folha;
import br.com.domain.Item;
import br.com.domain.Matriculaperiodo;
import br.com.dto.ItemFolhaDTO;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.service.ReportService;
import br.com.service.reports.ReportModel;
import br.com.sisbolsa.util.Constantes;
@ManagedBean
@ViewScoped
public class relatoriosController  implements Serializable{
	
	@EJB
	ReportService reportService;
	
	private static final long serialVersionUID = -6773170615007046046L;
	private String numeroCI;
	private Folha folhaSelecionado;

	public relatoriosController() {
	
	}
	
	public String getNumeroCI() {
		return numeroCI;
	}

	public void setNumeroCI(String numeroCI) {
		this.numeroCI = numeroCI;
	}

	public Folha getFolhaSelecionado() {
		return folhaSelecionado;
	}

	public void setFolhaSelecionado(Folha folhaSelecionado) {
		this.folhaSelecionado = folhaSelecionado;
	}

	public DefaultStreamedContent imprimirFolha(){
		QueryListResult<Item> query = QueryManager.FOLHA.findFolhasWithCollections()
												   .withFolha(this.folhaSelecionado);
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("numCI", this.numeroCI);
		 
		try {
			return new DefaultStreamedContent(reportService.gerar(ReportModel.FOLHA_DE_PAGAMENTOS,params,ItemFolhaDTO.getList(Repositorio.executeQuery(query))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}
	
	public DefaultStreamedContent imprimirRecibo(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		QueryListResult<Matriculaperiodo> query = QueryManager.FOLHA.findMatriculaPeriodoByPeriodoLetivoOrderedByCurso()
															  .withPeriodoLetivo(this.folhaSelecionado.getPeriodoletivo());
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("logo", contexto.getExternalContext().getRealPath(Constantes.LOGO));
		params.put("ref", folhaSelecionado.getReferencia());
		 
		try {
			return  new DefaultStreamedContent( reportService.gerar(ReportModel.RECIBO_DE_BOLETOS,params,Repositorio.executeQuery(query)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public DefaultStreamedContent imprimirConferenciaFolha(){
		QueryListResult<Matriculaperiodo> query = QueryManager.FOLHA.findMatriculaperiodoNotInFolha()
															  .withFolha(this.folhaSelecionado);
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ref", folhaSelecionado.getReferencia());
		 
		try {
			return  new DefaultStreamedContent(reportService.gerar(ReportModel.CONFERENCIA_FOLHA,params,Repositorio.executeQuery(query)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}

}
