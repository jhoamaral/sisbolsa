package br.com.sisbolsa.controller.folha;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Evento;
import br.com.domain.Historicovalor;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.service.ServiceBoleto;
import br.com.service.ServiceEvento;
import br.com.service.UtilService;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class EventoController extends AbstractController<Evento> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;
	private Historicovalor ultimoHistoricovalor;
	private BigDecimal ultimoValor;
	
	@EJB
	ServiceEvento serviceEvento;
	
	@EJB
	ServiceBoleto serviceBoleto;

	public EventoController() {
		super(Repositorio.GetInstance(Evento.class), 3,"descricao");
	}
	
	
	@Override
	public void setObject(Evento obj) {	
		super.setObject(obj);
		
		try{
			this.setUltimoHistoricovalor(serviceEvento.getUltimoHistoricoEnvento(this.obj));
			this.ultimoValor = this.getUltimoHistoricovalor().getValor();
		}catch (NoRecordFoundException e) {
			this.setUltimoHistoricovalor(new Historicovalor());
			this.getUltimoHistoricovalor().setId(UtilService.generateOid());
			this.getUltimoHistoricovalor().setData(new Timestamp(System.currentTimeMillis()));
			this.getUltimoHistoricovalor().setEvento(this.obj);
		}
		Repositorio<Historicovalor> getValores = Repositorio.GetInstance(Historicovalor.class);
		getValores.addEquals("evento", this.obj);
		this.obj.setHistoricovalors(getValores.getAllSet());
	}
	
	@Override
	protected String getFieldValue(Evento obj, int i) {
		switch (i) {
		case 0:
			return obj.getDescricao();
		case 1:
			return obj.getTipoevento().getDescricao();
		case 2:
			return obj.getCodigo();
		default:
			return "";
		}
	}

	public Historicovalor getUltimoHistoricovalor() {
		return ultimoHistoricovalor;
	}


	public void setUltimoHistoricovalor(Historicovalor ultimoHistoricovalor) {
		this.ultimoHistoricovalor = ultimoHistoricovalor;
	}


	@Override
	public void novo() {
		this.obj = new Evento();
		this.setFiltro("");
		this.setUltimoHistoricovalor(new Historicovalor());
		this.getUltimoHistoricovalor().setEvento(this.obj);
		this.getUltimoHistoricovalor().setId(UtilService.generateOid());
		this.getUltimoHistoricovalor().setData(new Timestamp(System.currentTimeMillis()));
		this.obj.setHistoricovalors(new HashSet<Historicovalor>());
		this.obj.getHistoricovalors().add(this.getUltimoHistoricovalor());
		this.ultimoValor = null;
		
	}
	
	@Override
	public void salvar() {
		if(this.ultimoValor instanceof BigDecimal){
			if(!this.getUltimoHistoricovalor().getValor().equals(ultimoValor)){
				Repositorio.GetInstance(Evento.class).getEntityManager().detach(this.getUltimoHistoricovalor());
				this.getUltimoHistoricovalor().setId(UtilService.generateOid());
				this.getUltimoHistoricovalor().setData(new Timestamp(System.currentTimeMillis()));
				this.obj.getHistoricovalors().add(this.getUltimoHistoricovalor());
			}
		}
		super.salvar();
	}

	

}
