package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Periodoletivo;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class PeriodoletivoController extends AbstractController<Periodoletivo> implements Serializable{

	private static final long serialVersionUID = 1L;

	public PeriodoletivoController() {
		super( 2,"semestre DESC, ano DESC");
	}

	@Override
	protected String getFieldValue(Periodoletivo obj, int i) {
		switch (i) {
		case 0:
			return obj.getAno().toString();
		case 1:
			return obj.getSemestre().toString();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Periodoletivo());
		this.setFiltro("");
		
	}


	

}
