package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Curso;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class CursoController extends AbstractController<Curso> implements Serializable{

	private static final long serialVersionUID = 1L;

	public CursoController() {
		super(1,"descricao");
	}

	@Override
	protected String getFieldValue(Curso obj, int i) {
		switch (i) {
		case 0:
			return obj.getDescricao();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Curso());
		this.setFiltro("");
		
	}


	

}
