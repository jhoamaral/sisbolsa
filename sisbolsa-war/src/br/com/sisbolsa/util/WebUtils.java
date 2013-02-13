package br.com.sisbolsa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.domain.Periodoletivo;
import br.com.generic.AbstractEntityDomain;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.service.UtilService;

public abstract class WebUtils {

	private static HashMap<String, List<SelectItem>> selectItemPool;

	public static <T extends AbstractEntityDomain> List<SelectItem> getComboBox(Class<T> classe) {
		if (!(selectItemPool instanceof HashMap)) {
			selectItemPool = new HashMap<String, List<SelectItem>>();
		}

		if (!selectItemPool.containsKey(classe)) {
			List<SelectItem> lista = new ArrayList<SelectItem>();
			QueryListResult<T> query = QueryManager.GENERIC.allObejctOrdered(classe).withOrder("descricao");
			for (T obj : Repositorio.executeQuery(query)) {
				lista.add(new SelectItem(obj, UtilService.invocaMetodo(obj, "getDescricao").toString()));
			}
			selectItemPool.put(classe.getName(), lista);
		}

		return selectItemPool.get(classe.getName());
	}

	public static <T> void refreshComboBox(Class<T> classe) {
		if (selectItemPool instanceof HashMap && selectItemPool.containsKey(classe.getName())) {
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

	public static List<SelectItem> getMesesDoPeriodoComboBox(Periodoletivo periodoletivo) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		DateFormat format = new SimpleDateFormat("MMM/yyyy");
		for (Date obj : getMesesDoPeriodo(periodoletivo)) {
			lista.add(new SelectItem(String.valueOf(obj.getTime()), format.format(obj)));
		}
		return lista;
	}
}
