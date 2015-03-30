package br.com.sisbolsa.controller.relatorios;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;

import br.com.domain.Item;
import br.com.dto.ExtratoFolhaDTO;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.service.ReportService;
import br.com.service.reports.ReportModel;

@ManagedBean
@ViewScoped
public class ExtratoFolhaController  implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;
	private String anoSelecionado;
	
	@EJB
	ReportService reportService;

	public ExtratoFolhaController() {
	
	}
	
	public String getAnoSelecionado() {
		return anoSelecionado;
	}

	public void setAnoSelecionado(String anoSelecionado) {
		this.anoSelecionado = anoSelecionado;
	}

	public DefaultStreamedContent imprimir(){
		QueryListResult<Item> query = QueryManager.ITEM.findItemByAno()
				 								  .withAno(getAnoSelecionado());
		try {
			return new DefaultStreamedContent(reportService.gerar(ReportModel.EXTRATO_FOLHA	,ExtratoFolhaDTO.getList(Repositorio.executeQuery(query))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
