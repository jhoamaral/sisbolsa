package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Estadocivil;
import br.com.repositorio.Repositorio;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class EstadoCivilController extends AbstractController<Estadocivil> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;

	public EstadoCivilController() {
		super(Repositorio.GetInstance(Estadocivil.class), 1,"descricao");
	}

	@Override
	protected String getFieldValue(Estadocivil obj, int i) {
		switch (i) {
		case 0:
			return obj.getDescricao();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Estadocivil());
		this.setFiltro("");
		
	}


	

}
