package br.com.sisbolsa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityNotFoundException;

import br.com.domain.Periodoletivo;
import br.com.repositorio.Repositorio;
import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.service.UtilService;


public abstract class WebUtils {

	private static HashMap<String, List<SelectItem>> selectItemPool;

	public static <T> List<SelectItem> getComboBox(Class<T> classe) {
		if (!(selectItemPool instanceof HashMap)) {
			selectItemPool = new HashMap<String, List<SelectItem>>();
		}

		if (!selectItemPool.containsKey(classe)) {
			List<SelectItem> lista = new ArrayList<SelectItem>();
			Repositorio<T> getObj;

			getObj = Repositorio.GetInstance(classe);
			getObj.addOrder("descricao");
			for (T obj : getObj.getAllList()) {
				lista.add(new SelectItem(obj, UtilService.invocaMetodo(obj,
						"getDescricao").toString()));
			}
			selectItemPool.put(classe.getName(), lista);

		}

		return selectItemPool.get(classe.getName());
	}

	public static <T> void refreshComboBox(Class<T> classe) {
		if (selectItemPool instanceof HashMap
				&& selectItemPool.containsKey(classe.getName())) {
			selectItemPool.remove(classe.getName());
		}
	}

	private static List<Date> getMesesDoPeriodo(Periodoletivo periodoletivo) {
		List<Date> meses = new ArrayList<Date>();
		int primeiroMes = 0;
		int qntMeses = 0;

		if (periodoletivo.getSemestre() == 1) {
			primeiroMes = 1;
			qntMeses = 6;
		} else {
			primeiroMes = 7;
			qntMeses = 5;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.YEAR, periodoletivo.getAno());
		for (int i = primeiroMes; i <= qntMeses; i++) {
			cal.set(Calendar.MONTH, i);
			meses.add(new Date(cal.getTimeInMillis()));
		}
		return meses;
	}

	public static List<SelectItem> getMesesDoPeriodoComboBox(
			Periodoletivo periodoletivo) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		DateFormat format = new SimpleDateFormat("MMM/yyyy");
		for (Date obj : getMesesDoPeriodo(periodoletivo)) {
			lista.add(new SelectItem(String.valueOf(obj.getTime()), format
					.format(obj)));
		}
		return lista;
	}

	public static Periodoletivo getPeriodoletivoAtual()
			throws EntityNotFoundException {
		Calendar cal = Calendar.getInstance();
		int mes = cal.get(Calendar.MONTH);
		int semestre;
		int ano = cal.get(Calendar.YEAR);
		if (mes <= 6) {
			semestre = 1;
		} else {
			semestre = 2;
		}

		Periodoletivo periodoletivo = new Periodoletivo();
		Repositorio<Periodoletivo> getPeriodo = Repositorio
				.GetInstance(Periodoletivo.class);
		getPeriodo.addEquals("ano", ano);
		getPeriodo.addEquals("semestre", semestre);
		try {
			periodoletivo = getPeriodo.getFirstRow();
		} catch (NoRecordFoundException e) {}

		return periodoletivo;
	}
}
