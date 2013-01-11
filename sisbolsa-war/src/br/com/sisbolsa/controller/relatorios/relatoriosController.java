package br.com.sisbolsa.controller.relatorios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
		Repositorio<Item> getFolha = Repositorio.GetInstance(Item.class);
		getFolha.setAlias("b");
		getFolha.join("LEFT join FETCH b.eventocauculados c");
		getFolha.join("LEFT join FETCH b.matriculaperiodo d");
		getFolha.join("LEFT join FETCH d.matricula e");
		getFolha.join("LEFT join FETCH e.pessoa f");
		getFolha.addEquals("b.folha", this.folhaSelecionado);
		getFolha.addOrder("e.cursofaculdade.faculdade.sigla asc,f.nome asc");
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("numCI", this.numeroCI);
		 
		try {
			return new DefaultStreamedContent(reportService.gerar(ReportModel.FOLHA_DE_PAGAMENTOS,params,ItemFolhaDTO.getList(getFolha.getAllList())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}
	
	public DefaultStreamedContent imprimirRecibo(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		Repositorio<Matriculaperiodo> getMatriculas = Repositorio.GetInstance(Matriculaperiodo.class);
		getMatriculas.setAlias("b");
		getMatriculas.addEquals("periodoletivo",  folhaSelecionado.getPeriodoletivo());
		getMatriculas.addOrder("b.matricula.cursofaculdade.faculdade.sigla asc,b.matricula.pessoa.nome asc");
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("logo", contexto.getExternalContext().getRealPath(Constantes.LOGO));
		params.put("ref", folhaSelecionado.getReferencia());
		 
		try {
			return  new DefaultStreamedContent( reportService.gerar(ReportModel.RECIBO_DE_BOLETOS,params,getMatriculas.getAllList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public DefaultStreamedContent imprimirConferenciaFolha(){
		Repositorio<Matriculaperiodo> getFolha = Repositorio.GetInstance(Matriculaperiodo.class);
		@SuppressWarnings("unchecked")
		List<Matriculaperiodo> values = getFolha.getEntityManager().createQuery("select o from Matriculaperiodo o where o not in("+
																	"select a from Matriculaperiodo a "+
																	"inner join a.items b "+
																	"inner join b.folha c "+
																	"where c = '"+this.folhaSelecionado+"' "+
																	") and o.periodoletivo = '"+this.folhaSelecionado.getPeriodoletivo()+"' " +
																	"order by o.matricula.cursofaculdade.faculdade.sigla asc, o.matricula.pessoa.nome asc").getResultList();
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ref", folhaSelecionado.getReferencia());
		 
		try {
			return  new DefaultStreamedContent(reportService.gerar(ReportModel.CONFERENCIA_FOLHA,params,values));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}

}
