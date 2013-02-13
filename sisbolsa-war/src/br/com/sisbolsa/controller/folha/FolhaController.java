package br.com.sisbolsa.controller.folha;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.domain.Folha;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class FolhaController extends AbstractController<Folha> implements Serializable{
	
	private static final long serialVersionUID = -6773170615007046046L;

	public FolhaController() {
		super(5, "dataFolha");
	}

	@Override
	protected String getFieldValue(Folha obj, int i) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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
		this.setObject(new Folha());
		this.getObject().setDataFolha(new Date(System.currentTimeMillis()));
		this.setFiltro("");
	}


	

}
