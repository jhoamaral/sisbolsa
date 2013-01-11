package br.com.sisbolsa.controller.bolsista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityNotFoundException;

import org.primefaces.model.DefaultStreamedContent;

import br.com.domain.Boleto;
import br.com.domain.Eventocauculado;
import br.com.domain.Familiar;
import br.com.domain.Item;
import br.com.domain.Matricula;
import br.com.domain.Matriculaperiodo;
import br.com.domain.Periodoletivo;
import br.com.domain.Socioeconeomico;
import br.com.domain.Tipoparentesco;
import br.com.dto.PessoaEndereco;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
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
public class MatriculaPeriodoController extends AbstractController<Matriculaperiodo> implements Serializable{
	
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
		super(Repositorio.GetInstance(Matriculaperiodo.class), 3,"matricula.pessoa.nome");
		
	}
	
	@PostConstruct
	public void postCostrutor(){
		try {
			this.periodoletivoSelecionado = servicePeriodoletivo.getPeriodoletivoAtual();
		} catch (NoRecordFoundException e) {}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Boleto> getBoletoList() {
		if (this.obj instanceof Matriculaperiodo) {
			try{
				return new ArrayList(this.obj.getBoletos());
			}catch (Exception e) {
				Repositorio<Boleto> getTelefones = Repositorio.GetInstance(Boleto.class);
				getTelefones.addEquals("matriculaperiodo", this.obj);
				this.obj.setBoletos(getTelefones.getAllSet());
				return new ArrayList(this.obj.getBoletos());
			} 
		} else {
			return new ArrayList();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Eventocauculado> getEventosList() {
		if (this.obj instanceof Matriculaperiodo) {
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
		if (this.obj instanceof Matriculaperiodo) {
			Repositorio<Item> getObjects = Repositorio.GetInstance(Item.class);
			getObjects.addEquals("matriculaperiodo", this.obj);
			getObjects.addOrder("folha.referencia desc");
			this.obj.setItems(getObjects.getAllSet());
			return new ArrayList(this.obj.getItems());
		} else {
			return new ArrayList();
		}
	}
	
	@Override
	protected String getFieldValue(Matriculaperiodo obj, int i) {
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
		this.setObject(new Matriculaperiodo());
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
		Repositorio<Eventocauculado> getEventos = Repositorio.GetInstance(Eventocauculado.class);
		getEventos.addEquals("item", this.itemSelecionado);
		this.itemSelecionado.setEventocauculados(getEventos.getAllSet());
	}

	public List<SelectItem> getListaMesesBoleto(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if(this.obj.getPeriodoletivo() instanceof Periodoletivo){
			lista = WebUtils.getMesesDoPeriodoComboBox(this.obj.getPeriodoletivo());
		}else{
			try{
				lista = WebUtils.getMesesDoPeriodoComboBox(WebUtils.getPeriodoletivoAtual());
			}catch (EntityNotFoundException e) {
			
			}
			
		}
		return lista;
	}

	public List<Matricula> completePessoa(String query) {  
		 List<Matricula> suggestions = new ArrayList<Matricula>();
		 
		 if(this.listPessoa.isEmpty()){
			 Repositorio<Matricula> getPessoas = Repositorio.GetInstance(Matricula.class);
			 this.listPessoa = getPessoas.getAllList();
		 }
	          
		 for(Matricula obj : this.listPessoa) {  
			 	if(obj.getPessoa().getNome().toLowerCase().startsWith(query.toLowerCase()) || 
			 	  String.valueOf(obj.getPessoa().getCpf().getNumero()).toLowerCase().startsWith(query.toLowerCase()) )  
			 	suggestions.add(obj);  
		 }  
	          
	 	return suggestions;  
	 }
	
	public void verificaSocioEconomico(){
		try{
			Socioeconeomico socioeconeomico = serviceBolsista.getUltimoSocioEconomico(this.obj.getMatricula());
			Socioeconeomico newSocioeconeomico = new Socioeconeomico();
			newSocioeconeomico.setId(UtilService.generateOid());
			newSocioeconeomico.setMatriculaperiodo(this.obj);
			
			
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
		Repositorio<Familiar> findMat = Repositorio.GetInstance(Familiar.class);
		findMat.addEquals("pessoa", this.obj.getMatricula().getPessoa());
		List<Familiar> lista = findMat.getAllList();
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
		Repositorio<Matriculaperiodo> findMat = Repositorio.GetInstance(Matriculaperiodo.class);
		findMat.setAlias("a");
		findMat.join("left join fetch a.matricula b");
		findMat.join("left join fetch b.pessoa c");
		findMat.join("left join fetch c.telefones d");
		findMat.addEquals("a", this.obj);
		findMat.setLimit(1);
		Map<String,String> params = new HashMap<String, String>();	
		params.put("logo", contexto.getExternalContext().getRealPath(Constantes.LOGO));
		params.put("periodo",String.valueOf(UtilService.calculaPeriodoAtual(this.obj.getMatricula().getPeriodoletivo(),this.obj.getPeriodoletivo()))); 
		try {
			return new DefaultStreamedContent(reportService.gerar(ReportModel.FICHA_CADASTRO,params,findMat.getAllList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}
	
	public void addBoleto() {
		try {
			this.getBoletoSelecionado().setMatriculaperiodo(this.obj);
			if (this.obj.getId() instanceof String) {
				Repositorio<Boleto> save = Repositorio.GetInstance(Boleto.class);
				save.cadastrar(this.getBoletoSelecionado());

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
			if ((this.obj.getId() instanceof String)
					&& (this.getBoletoSelecionado().getId() instanceof String)) {
				Repositorio<Boleto> save = Repositorio
						.GetInstance(Boleto.class);
				save.delete(this.getBoletoSelecionado());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setBoletoSelecionado(new Boleto());

	}
	
	public void carregaAllObjects(){
		repositorio.clear();
		repositorio.addOrder(this.getOrderField());
		repositorio.addEquals("periodoletivo", this.periodoletivoSelecionado);
		this.setAllObj(repositorio.getAllList());
	}

}
