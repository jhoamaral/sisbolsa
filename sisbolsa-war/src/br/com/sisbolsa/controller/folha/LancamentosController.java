package br.com.sisbolsa.controller.folha;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;

import br.com.domain.Boleto;
import br.com.domain.Evento;
import br.com.domain.Eventocauculado;
import br.com.domain.Folha;
import br.com.domain.Item;
import br.com.domain.Matriculaperiodo;
import br.com.domain.Periodoletivo;
import br.com.domain.Usuario;
import br.com.dto.ItemFolhaDTO;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.service.ReportService;
import br.com.service.ServiceBoleto;
import br.com.service.ServicePeriodoletivo;
import br.com.service.UtilService;
import br.com.service.reports.ReportModel;
import br.com.sisbolsa.controller.AbstractController;
import br.com.sisbolsa.util.Constantes;

@ManagedBean
@ViewScoped
public class LancamentosController extends AbstractController<Folha> implements
		Serializable {

	private static final long serialVersionUID = -6773170615007046046L;
	private List<Evento> listEvento = new ArrayList<Evento>();
	private List<Matriculaperiodo> listMatricula = new ArrayList<Matriculaperiodo>();
	private Eventocauculado novoEvento;
	private Item novoItem;
	private boolean valorBloqueado = true;
	private Boleto boletoSelecionado;
	private String filtroItens = "";
	private BigDecimal diferenca;
	private boolean existeDiferenca = false;
	private int progress;

	private Item itemSelecionado = new Item();

	private Periodoletivo periodoletivoSelecionado;

	@ManagedProperty(value = "#{loginController.user}")
	private Usuario user;

	@EJB
	private ServicePeriodoletivo servicePeriodoletivo;

	@EJB
	private ServiceBoleto serviceBoleto;
	
	@EJB
	private ReportService reportService;

	public LancamentosController() {
		super(Repositorio.GetInstance(Folha.class), 5,
				"periodoletivo.ano desc, periodoletivo.semestre desc, referencia");
	}

	@PostConstruct
	public void postContruct() {
		try {
			this.periodoletivoSelecionado = servicePeriodoletivo
					.getPeriodoletivoAtual();
		} catch (NoRecordFoundException e) {
		}
		this.novo();
	}

	@Override
	public void setObject(Folha obj) {
		super.setObject(obj);

		Repositorio<Item> getItem = Repositorio.GetInstance(Item.class);
		getItem.addEquals("folha", this.obj);
		this.obj.setItems(getItem.getAllSet());
	}

	@Override
	protected String getFieldValue(Folha obj, int i) {
		DateFormat format = new SimpleDateFormat("MMM/yyyy");
		switch (i) {
		case 0:
			return obj.getTipo();
		case 1:
			return format.format(obj.getDataFolha());
		case 2:
			return format.format(obj.getReferencia());
		case 3:
			return obj.getPeriodoletivo().getAno().toString();
		case 4:
			return obj.getPeriodoletivo().getSemestre().toString();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.obj = new Folha();
		this.setNovoEvento(new Eventocauculado());
		this.setNovoItem(new Item());
		this.obj.setItems(new HashSet<Item>());
		this.novoItem.setMatriculaperiodo(new Matriculaperiodo());
		this.setFiltro("");
		this.boletoSelecionado = null;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Item> getListItens() {
		List<Item> retorno = new ArrayList<Item>();
		if (this.getFiltroItens().equals("")) {
			retorno = new ArrayList<Item>(this.obj.getItems());
		} else {
			for (Item item : this.obj.getItems()) {
				try {
					if (item.getMatriculaperiodo().getMatricula().getPessoa()
							.getNome().toLowerCase()
							.indexOf(this.getFiltroItens().toLowerCase()) > -1) {
						retorno.add(item);
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}

		return retorno;
	}

	public List<Eventocauculado> getListEventos() {
		return new ArrayList<Eventocauculado>(
				this.novoItem.getEventocauculados());
	}

	public Eventocauculado getNovoEvento() {
		return novoEvento;
	}

	public void setNovoEvento(Eventocauculado novoEvento) {
		this.novoEvento = novoEvento;
	}

	public Item getNovoItem() {
		return novoItem;
	}

	public void setNovoItem(Item novoItem) {
		this.novoItem = novoItem;
		try {
			this.boletoSelecionado = serviceBoleto
					.getUltimoBoleto(this.novoItem.getMatriculaperiodo());
		} catch (NoRecordFoundException e) {
		} catch (NullPointerException e) {
		}
		Repositorio<Eventocauculado> repositorio = Repositorio.GetInstance(Eventocauculado.class);
		repositorio.addEquals("itemid", this.novoItem);
		this.novoItem.setEventocauculados(repositorio.getAllSet());
		

	}

	public Boleto getBoletoSelecionado() {
		return boletoSelecionado;
	}

	public BigDecimal getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}

	public boolean isExisteDiferenca() {
		return existeDiferenca;
	}

	public void setExisteDiferenca(boolean existeDiferenca) {
		this.existeDiferenca = existeDiferenca;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getFiltroItens() {
		return filtroItens;
	}

	public void setFiltroItens(String filtroItens) {
		this.filtroItens = filtroItens;
	}

	public void setBoletoSelecionado(Boleto boletoSelecionado) {
		this.boletoSelecionado = boletoSelecionado;
	}

	public boolean isValorBloqueado() {
		return valorBloqueado;
	}

	public void setValorBloqueado(boolean valorBloqueado) {
		this.valorBloqueado = valorBloqueado;
	}

	@Override
	public void carregaAllObjects() {
		repositorio.addEquals("aberto", true);
		repositorio.addOrder(this.getOrderField());
		this.setAllObj(repositorio.getAllList());
	}

	public List<Evento> completeEvento(String query) {
		List<Evento> suggestions = new ArrayList<Evento>();
		if (this.listEvento.isEmpty()) {
			Repositorio<Evento> getObjs = Repositorio.GetInstance(Evento.class);
			this.listEvento = getObjs.getAllList();
		}
		for (Evento obj : this.listEvento) {
			if (obj.getCodigo().toLowerCase().startsWith(query.toLowerCase())
					|| obj.getDescricao().toLowerCase()
							.startsWith(query.toLowerCase()))
				suggestions.add(obj);
		}
		return suggestions;
	}

	public List<Matriculaperiodo> completeMatricula(String query) {
		List<Matriculaperiodo> suggestions = new ArrayList<Matriculaperiodo>();

		if (this.listMatricula.isEmpty() && this.obj instanceof Folha) {
			this.carregaListMatricula();
		}

		for (Matriculaperiodo obj : this.listMatricula) {
			if (obj.getMatricula().getPessoa().getNome().toLowerCase()
					.startsWith(query.toLowerCase())
					|| obj.getMatricula().getPessoa().getCpf().getNumero()
							.toLowerCase().startsWith(query.toLowerCase()))
				suggestions.add(obj);
		}

		return suggestions;
	}

	public void carregaListMatricula() {
		Repositorio<Matriculaperiodo> getObjs = Repositorio
				.GetInstance(Matriculaperiodo.class);
		getObjs.addEquals("periodoletivo", this.periodoletivoSelecionado);
		this.listMatricula = getObjs.getAllList();
	}

	public void carregaUltimoBoleto(SelectEvent event) {
		try {
			Repositorio<Item> getItem = Repositorio.GetInstance(Item.class);
			getItem.addEquals("matriculaperiodo",
					(Matriculaperiodo) event.getObject());
			getItem.addEquals("folha", this.obj);
			this.setNovoItem(getItem.getFirstRow());

		} catch (NoRecordFoundException e) {
			this.novoItem.setMatriculaperiodo((Matriculaperiodo) event
					.getObject());
		}

		try {
			this.boletoSelecionado = serviceBoleto
					.getUltimoBoleto(this.novoItem.getMatriculaperiodo());
		} catch (NoRecordFoundException e) {
		}
	}

	public List<SelectItem> getListaBoleto() {
		DateFormat format = new SimpleDateFormat("MMM/yyyy");
		List<SelectItem> lista = new ArrayList<SelectItem>();
		Repositorio<Boleto> getObj = Repositorio.GetInstance(Boleto.class);
		getObj.addEquals("matriculaperiodo",
				this.novoItem.getMatriculaperiodo());
		getObj.addOrder("data");
		for (Boleto obj : getObj.getAllList()) {
			lista.add(new SelectItem(obj, format.format(obj.getData())));
		}

		return lista;
	}

	public void calculuaEvento(SelectEvent event) {
		this.novoEvento.setEvento((Evento) event.getObject());
		BigDecimal valor = BigDecimal.ZERO;
		try {
			valor = serviceBoleto.calculaEvento(this.novoEvento.getEvento(),
					this.boletoSelecionado.getValor());

			if (valor.equals(BigDecimal.ZERO)) {
				this.valorBloqueado = false;
			} else {
				this.novoEvento.setValor(valor);
				this.valorBloqueado = true;
			}

			this.setDiferenca(serviceBoleto.calculaDiferenca(
					this.novoEvento.getEvento(), this.boletoSelecionado));

			if (this.getDiferenca().equals(BigDecimal.ZERO)) {
				setExisteDiferenca(false);
			} else {
				setExisteDiferenca(true);
			}
		} catch (NoRecordFoundException e) {
		}
	}

	public void addDiferenca() {
		Evento evento;
		try {
			if (this.diferenca.compareTo(BigDecimal.ZERO) == 1) {
				evento = Repositorio.GetInstance(Evento.class).getById(
						Constantes.DIFERENCA_CREDITO);
			} else {
				evento = Repositorio.GetInstance(Evento.class).getById(
						Constantes.DIFERENCA_DEDUCAO);
			}

			this.novoEvento.setEvento(evento);
			DateFormat format = new SimpleDateFormat("MMMM");
			this.novoEvento.setDescricao(format.format(this.boletoSelecionado
					.getData()));
			this.novoEvento.setValor(this.diferenca.abs());
			this.addEvento();
		} catch (NoRecordFoundException e) {
		}
	}

	public void calculuaItem() {
		this.novoItem.setValor(novoItem.getValor().multiply(BigDecimal.ZERO));
		for (Eventocauculado evento : this.novoItem.getEventocauculados()) {
			if (evento.getEvento().getTipoevento().getId().equals("001")) {
				this.novoItem.setValor(novoItem.getValor().subtract(
						evento.getValor()));
			} else {
				this.novoItem.setValor(novoItem.getValor().add(
						evento.getValor()));
			}
		}
	}

	public void configItem() {
		this.setNovoItem(new Item());
		this.setNovoEvento(new Eventocauculado());
		this.periodoletivoSelecionado = this.obj.getPeriodoletivo();
		this.carregaListMatricula();
		this.novoItem.setMatriculaperiodo(new Matriculaperiodo());
		this.boletoSelecionado = new Boleto();
	}

	public void addItem() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (this.novoItem.getMatriculaperiodo() instanceof Matriculaperiodo) {
				this.novoItem.setFolha(this.obj);
				Repositorio<Item> saveObj = Repositorio.GetInstance(Item.class);
				this.calculuaItem();
				this.obj.getItems().add(this.novoItem);
				if (this.novoItem.getId() instanceof String) {
					saveObj.editar(this.novoItem);
				} else {
					saveObj.cadastrar(this.novoItem);
				}
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Item adicionado com sucesso! "));
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ocorreu um erro!", e.getMessage()));
		}
		this.configItem();
	}

	public void removeItem() {
		try {
			this.obj.getItems().remove(this.novoItem);
			if (this.novoItem.getId() instanceof String) {
				Repositorio<Item> saveObj = Repositorio.GetInstance(Item.class);
				saveObj.delete(this.novoItem);
				this.boletoSelecionado = new Boleto();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.configItem();
	}

	public void addEvento() {
		try {
			this.novoEvento.setItem(this.novoItem);
			this.novoEvento.setUsuario(this.getUser());

			if (this.novoItem.getId() instanceof String) {
				Repositorio<Eventocauculado> saveObj = Repositorio
						.GetInstance(Eventocauculado.class);
				saveObj.cadastrar(this.novoEvento);
			} else {
				this.novoEvento.setId(UtilService.generateOid());
			}
			this.novoItem.getEventocauculados().add(this.novoEvento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setNovoEvento(new Eventocauculado());
	}

	public void removeEvento() {
		try {
			this.novoItem.getEventocauculados().remove(this.novoEvento);
			if (this.novoItem.getId() instanceof String) {
				Repositorio<Eventocauculado> saveObj = Repositorio
						.GetInstance(Eventocauculado.class);
				saveObj.delete(this.novoEvento);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setNovoEvento(new Eventocauculado());
	}

	public void calculaFolha() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			BigDecimal valor = BigDecimal.ZERO;

			for (Item item : this.obj.getItems()) {
				valor = valor.add(item.getValor());
			}

			this.obj.setValorTotal(valor);
			Repositorio<Folha> saveObj = Repositorio.GetInstance(Folha.class);

			saveObj.editar(this.obj);
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Sucesso!",
					"Calculo realizado com sucesso! "));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Erro!",
					"Ocorreu um erro ao realizar o calculo! "));
			e.printStackTrace();
		}
	}

	public DefaultStreamedContent imprimirFolha() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		Repositorio<Item> getFolha = Repositorio.GetInstance(Item.class);
		getFolha.setAlias("b");
		getFolha.join("LEFT join FETCH b.eventocauculados c");
		getFolha.join("LEFT join FETCH b.matriculaperiodo d");
		getFolha.join("LEFT join FETCH d.matricula e");
		getFolha.join("LEFT join FETCH e.pessoa f");
		getFolha.addOrder("e.cursofaculdade.faculdade.sigla asc,f.nome asc");

		Map<String, String> params = new HashMap<String, String>();
		params.put("logo",
				contexto.getExternalContext().getRealPath(Constantes.LOGO));

		try {
			return new DefaultStreamedContent( reportService.gerar(ReportModel.FOLHA_DE_PAGAMENTOS,
					ItemFolhaDTO.getList(getFolha.getAllList())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public DefaultStreamedContent imprimirRtecibo() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		Repositorio<Matriculaperiodo> getMatriculas = Repositorio
				.GetInstance(Matriculaperiodo.class);
		getMatriculas.setAlias("b");
		getMatriculas
				.addOrder("b.matricula.cursofaculdade.faculdade.sigla asc,b.matricula.pessoa.nome asc");

		Map<String, String> params = new HashMap<String, String>();
		params.put("logo",
				contexto.getExternalContext().getRealPath(Constantes.LOGO));

		try {
			return new DefaultStreamedContent( reportService.gerar(ReportModel.RECIBO_DE_BOLETOS,
					getMatriculas.getAllList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Item getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(Item itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Eventocauculado> getEventosList() {
		if (this.novoItem.getMatriculaperiodo() instanceof Matriculaperiodo) {
			try {
				return new ArrayList(this.itemSelecionado.getEventocauculados());
			} catch (Exception e) {
				this.findEventos();
				return new ArrayList(this.itemSelecionado.getEventocauculados());
			}
		} else {
			return new ArrayList();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Item> getItemsHistoricoList() {
		if (this.novoItem.getMatriculaperiodo() instanceof Matriculaperiodo) {
			Repositorio<Item> getObjects = Repositorio.GetInstance(Item.class);
			getObjects.addEquals("matriculaperiodo",
					this.novoItem.getMatriculaperiodo());
			getObjects.addOrder("folha.referencia desc");
			this.novoItem.getMatriculaperiodo()
					.setItems(getObjects.getAllSet());
			this.novoItem.getMatriculaperiodo().getItems()
					.remove(this.novoItem);
			return new ArrayList(this.novoItem.getMatriculaperiodo().getItems());
		} else {
			return new ArrayList();
		}
	}

	public void findEventos() {
		Repositorio<Eventocauculado> getEventos = Repositorio
				.GetInstance(Eventocauculado.class);
		getEventos.addEquals("item", this.itemSelecionado);
		this.itemSelecionado.setEventocauculados(getEventos.getAllSet());
	}

	public Periodoletivo getPeriodoletivoSelecionado() {
		return periodoletivoSelecionado;
	}

	public void setPeriodoletivoSelecionado(
			Periodoletivo periodoletivoSelecionado) {
		this.periodoletivoSelecionado = periodoletivoSelecionado;
	}

}
