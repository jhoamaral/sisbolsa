package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Tipotelefone;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class TipoTelefoneController extends AbstractController<Tipotelefone> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;

	public TipoTelefoneController() {
		super(1,"descricao");
	}

	@Override
	protected String getFieldValue(Tipotelefone obj, int i) {
		switch (i) {
		case 0:
			return obj.getDescricao();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Tipotelefone());
		this.setFiltro("");
		
	}


	

}
