package br.com.sisbolsa.controller.basicos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Logradouro;
import br.com.repositorio.Repositorio;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class LogradouroController extends AbstractController<Logradouro> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;

	public LogradouroController() {
		super(Repositorio.GetInstance(Logradouro.class), 4,"rua");
	}

	@Override
	protected String getFieldValue(Logradouro obj, int i) {
		switch (i) {
		case 0:
			return obj.getRua();
		case 1:
			return obj.getBairro();
		case 2:
			return obj.getCep();
		case 3:
			return obj.getCidade();
		default:
			return "";
		}
	}

	@Override
	public void novo() {
		this.setObject(new Logradouro());
		this.setFiltro("");
		
	}


	

}
