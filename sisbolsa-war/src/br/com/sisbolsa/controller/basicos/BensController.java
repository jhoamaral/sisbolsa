package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Ben;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class BensController extends AbstractController<Ben> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;

	public BensController() {
		super( 1,"descricao");
	}

	@Override
	protected String getFieldValue(Ben obj, int i) {
		switch (i) {
		case 0:
			return obj.getDescricao();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Ben());
		this.setFiltro("");
		
	}


	

}
