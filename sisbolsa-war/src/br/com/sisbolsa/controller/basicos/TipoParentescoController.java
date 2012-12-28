package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Tipoparentesco;
import br.com.repositorio.Repositorio;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class TipoParentescoController extends AbstractController<Tipoparentesco> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoParentescoController() {
		super(Repositorio.GetInstance(Tipoparentesco.class), 1,"descricao");
	}

	@Override
	protected String getFieldValue(Tipoparentesco obj, int i) {
		switch (i) {
		case 0:
			return obj.getDescricao();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Tipoparentesco());
		this.setFiltro("");
		
	}


	

}
