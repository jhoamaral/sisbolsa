package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Condicoesmoradia;
import br.com.repositorio.Repositorio;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class CondicoesMoradiaController extends AbstractController<Condicoesmoradia> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;

	public CondicoesMoradiaController() {
		super(Repositorio.GetInstance(Condicoesmoradia.class), 1,"descricao");
	}

	@Override
	protected String getFieldValue(Condicoesmoradia obj, int i) {
		switch (i) {
		case 0:
			return obj.getDescricao();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Condicoesmoradia());
		this.setFiltro("");
		
	}


	

}
