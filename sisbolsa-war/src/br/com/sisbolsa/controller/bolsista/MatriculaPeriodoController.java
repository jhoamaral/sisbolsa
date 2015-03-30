package br.com.sisbolsa.controller.bolsista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultStreamedContent;

import br.com.domain.Boleto;
import br.com.domain.Eventocauculado;
import br.com.domain.Familiar;
import br.com.domain.Item;
import br.com.domain.Matricula;
import br.com.domain.Ativobolsa;
import br.com.domain.Periodoletivo;
import br.com.domain.Socioeconeomico;
import br.com.domain.Tipoparentesco;
import br.com.dto.PessoaEndereco;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.repositorio.querybuilder.query.QuerySingleResult;
import br.com.service.ReportService;
import br.com.service.ServiceBolsista;
import br.com.service.ServicePeriodoletivo;
import br.com.service.UtilService;
import br.com.service.reports.ReportModel;
import br.com.sisbolsa.controller.AbstractController;
import br.com.sisbolsa.util.Constantes;
import br.com.sisbolsa.util.WebUtils;

@ManagedBean
@ViewScoped
public class MatriculaPeriodoController extends AbstractController<Ativobolsa> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;	
	private List<Matricula> listPessoa = new ArrayList<Matricula>();
	private Boleto boletoSelecionado = new Boleto();
	private Item itemSelecionado = new Item();
	
	@EJB
	private ServicePeriodoletivo servicePeriodoletivo;
	
	@EJB
	private ServiceBolsista serviceBolsista;
	
	@EJB
	ReportService reportService;
	
	private Periodoletivo periodoletivoSelecionado;

	public MatriculaPeriodoController() {
		super( 3,"matricula.pessoa.nome");
		
	}
	
	@PostConstruct
	public void postCostrutor(){
		try {
			this.periodoletivoSelecionado = servicePeriodoletivo.getPeriodoletivoAtual();
		} catch (NoRecordFoundException e) {}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Boleto> getBoletoList() {
		if (this.obj instanceof Ativobolsa) {
			try{
				return new ArrayList(this.obj.getBoletos());
			}catch (Exception e) {
				QueryListResult<Boleto> query = QueryManager.BOLETO.findBoletoByAtivobolsa()
															.withAtivobolsa(this.obj);
				Set<Boleto> lista = new LinkedHashSet<Boleto>(Repositorio.executeQuery(query));
				this.obj.setBoletos(lista);
				return new ArrayList(this.obj.getBoletos());
			} 
		} else {
			return new ArrayList();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Eventocauculado> getEventosList() {
		if (this.obj instanceof Ativobolsa) {
			try{
				return new ArrayList(this.itemSelecionado.getEventocauculados());
			}catch (Exception e) {
				this.findEventos();
				return new ArrayList(this.itemSelecionado.getEventocauculados());
			} 
		} else {
			return new ArrayList();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Item> getItemsList() {
		if (this.obj instanceof Ativobolsa) {
			QueryListResult<Item> query = QueryManager.ITEM.findItemByAtivobolsa()
													  .withAtivobolsa(this.obj);
			Set<Item> lista = new LinkedHashSet<Item>(Repositorio.executeQuery(query));
			this.obj.setItems(lista);
			return new ArrayList(this.obj.getItems());
		} else {
			return new ArrayList();
		}
	}
	
	@Override
	protected String getFieldValue(Ativobolsa obj, int i) {
		switch (i) {
		case 0:
			return  String.valueOf(obj.getMatricula().getPessoa().getNome());
		case 1:
			return String.valueOf(obj.getPeriodoletivo().getSemestre());
		case 2:
			return String.valueOf(obj.getPeriodoletivo().getAno());
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Ativobolsa());
		this.setFiltro("");
		this.obj.setItems(new HashSet<Item>());
		this.boletoSelecionado = new Boleto();
	}
	
	 public Boleto getBoletoSelecionado() {
		return boletoSelecionado;
	}

	public void setBoletoSelecionado(Boleto boletoSelecionado) {
		this.boletoSelecionado = boletoSelecionado;
	}

	public Periodoletivo getPeriodoletivoSelecionado() {
		return periodoletivoSelecionado;
	}

	public void setPeriodoletivoSelecionado(Periodoletivo periodoletivoSelecionado) {
		this.periodoletivoSelecionado = periodoletivoSelecionado;
	}

	public Item getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(Item itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}
	
	public void findEventos(){		
		QueryListResult<Eventocauculado> query = QueryManager.EVENTO.findEventoCalculadoByItem()
															 .withItem(this.itemSelecionado);
		Set<Eventocauculado> lista = new LinkedHashSet<Eventocauculado>(Repositorio.executeQuery(query));
		this.itemSelecionado.setEventocauculados(lista);
	}

	public List<SelectItem> getListaMesesBoleto(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if(this.obj.getPeriodoletivo() instanceof Periodoletivo){
			lista = WebUtils.getMesesDoPeriodoComboBox(this.obj.getPeriodoletivo());
		}else{
			try{
				lista = WebUtils.getMesesDoPeriodoComboBox(servicePeriodoletivo.getPeriodoletivoAtual());
			}catch (NoRecordFoundException e) {}
		}
		return lista;
	}

	public List<Matricula> completePessoa(String suggest) {  
		 List<Matricula> suggestions = new ArrayList<Matricula>();
		 
		 if(this.listPessoa.isEmpty()){
			 QueryListResult<Matricula> query = QueryManager.GENERIC.allObejctOrdered(Matricula.class)
					 										.withOrder("pessoa.nome");
			 this.listPessoa = Repositorio.executeQuery(query);
		 }
	          
		 for(Matricula obj : this.listPessoa) {  
			 	if(obj.getPessoa().getNome().toLowerCase().startsWith(suggest.toLowerCase()) || 
			 	  String.valueOf(obj.getPessoa().getCpf().getNumero()).toLowerCase().startsWith(suggest.toLowerCase()) )  
			 	suggestions.add(obj);  
		 }  
	          
	 	return suggestions;  
	 }
	
	public void verificaSocioEconomico(){
		try{
			Socioeconeomico socioeconeomico = serviceBolsista.getUltimoSocioEconomico(this.obj.getMatricula());
			Socioeconeomico newSocioeconeomico = new Socioeconeomico();
			newSocioeconeomico.setId(UtilService.generateOid());
			newSocioeconeomico.setAtivobolsa(this.obj);
			
			
			newSocioeconeomico.setCondicoesmoradia(socioeconeomico.getCondicoesmoradia());
			newSocioeconeomico.setInstalacoessanitarias(socioeconeomico.getInstalacoessanitarias());
			newSocioeconeomico.setRenda(socioeconeomico.getRenda());
			newSocioeconeomico.setSituacaojuridica(socioeconeomico.getSituacaojuridica());
			newSocioeconeomico.setSituacaooperacional(socioeconeomico.getSituacaooperacional());
			newSocioeconeomico.setTemporesidencia(socioeconeomico.getTemporesidencia());
			this.obj.setSocioeconeomico(newSocioeconeomico);
			
			this.obj.setPeriodoletivo(servicePeriodoletivo.getPeriodoletivoAtual());
		}catch (Exception e) {
		
		}
		
	}
	
	
	public DefaultStreamedContent imprimirDeclaracaoRenda(){
		QueryListResult<Familiar> query = QueryManager.PESSOA.findFamiliaresByPessoa()
													  .withPessoa(this.obj.getMatricula().getPessoa());
		List<Familiar> lista = Repositorio.executeQuery(query);
		
		Familiar parente = new Familiar();
		parente.setNome( this.obj.getMatricula().getPessoa().getNome());
		parente.setRenda( this.obj.getSocioeconeomico().getRenda());
		parente.setPessoa( this.obj.getMatricula().getPessoa());
		parente.setDataNascimento( this.obj.getMatricula().getPessoa().getDataNascimento());
		Tipoparentesco euMesmo = new Tipoparentesco();
		euMesmo.setDescricao("------");
		parente.setTipoparentesco(euMesmo);
		lista.add(parente);
		
		try {
			return new DefaultStreamedContent(reportService.gerar(ReportModel.DECLARACAO_RENDIMENTOS,PessoaEndereco.getObj(lista)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}
	
	public DefaultStreamedContent imprimirFicha(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		try {
			QuerySingleResult<Ativobolsa> query = QueryManager.PESSOA.findMatriculaPeriodoWithCollections()
																	.withAtivobolsa(this.obj);		
			List<Ativobolsa> lista = new ArrayList<Ativobolsa>();
			lista.add(Repositorio.executeQuery(query));
			
			Map<String,Object> params = new HashMap<String, Object>();	
			params.put("logo", contexto.getExternalContext().getRealPath(Constantes.LOGO));
			params.put("periodo",String.valueOf(UtilService.calculaPeriodoAtual(this.obj.getMatricula().getPeriodoletivo(),this.obj.getPeriodoletivo()))); 
			
			return new DefaultStreamedContent(reportService.gerar(ReportModel.FICHA_CADASTRO,params,lista));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}
	
	public void addBoleto() {
		try {
			this.getBoletoSelecionado().setAtivobolsa(this.obj);
			if (this.obj.getId() instanceof String) {
				Repositorio.cadastrar(this.getBoletoSelecionado());
			} else {
				this.getBoletoSelecionado().setId(UtilService.generateOid());
			}
			this.obj.getBoletos().add(this.getBoletoSelecionado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setBoletoSelecionado(new Boleto());

	}

	public void removeBoleto() {
		try {
			this.obj.getBoletos().remove(this.getBoletoSelecionado());
			if ((this.obj.getId() instanceof String) && (this.getBoletoSelecionado().getId() instanceof String)) {
				Repositorio.delete(this.getBoletoSelecionado());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setBoletoSelecionado(new Boleto());

	}
	
	@Override
	public void carregaAllObjects(){
		QueryListResult<Ativobolsa> query = QueryManager.PESSOA.findMatriculaPeriodoByPeriodoLetivo()
															  .withPeriodoLetivo(this.periodoletivoSelecionado);
		this.setAllObj(Repositorio.executeQuery(query));
	}

}
