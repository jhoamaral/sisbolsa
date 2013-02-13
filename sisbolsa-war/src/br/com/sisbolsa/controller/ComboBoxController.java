package br.com.sisbolsa.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.domain.Ben;
import br.com.domain.Condicoesmoradia;
import br.com.domain.Escolaridade;
import br.com.domain.Estadocivil;
import br.com.domain.Folha;
import br.com.domain.Periodoletivo;
import br.com.domain.Situacaojuridica;
import br.com.domain.Situacaooperacional;
import br.com.domain.Tipoevento;
import br.com.domain.Tipoparentesco;
import br.com.domain.Tipotelefone;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.sisbolsa.util.WebUtils;

@ManagedBean
@SessionScoped
public class ComboBoxController {

	public List<SelectItem> getListaEscolaridade() {
		return WebUtils.getComboBox(Escolaridade.class);
	}

	public List<SelectItem> getListaEstadoCivil() {
		return WebUtils.getComboBox(Estadocivil.class);
	}

	public List<SelectItem> getListaSituacaoJuridica() {
		return WebUtils.getComboBox(Situacaojuridica.class);
	}

	public List<SelectItem> getListaSituacaoOperacional() {
		return WebUtils.getComboBox(Situacaooperacional.class);
	}

	public List<SelectItem> getListaCondicoesMoradia() {
		return WebUtils.getComboBox(Condicoesmoradia.class);
	}

	public List<SelectItem> getListaTipoTelefone() {
		return WebUtils.getComboBox(Tipotelefone.class);
	}

	public List<SelectItem> getListaTipoParentesco() {
		return WebUtils.getComboBox(Tipoparentesco.class);
	}

	public List<SelectItem> getListaBen() {
		return WebUtils.getComboBox(Ben.class);
	}

	public List<SelectItem> getListaCondicaoesMoradia() {
		return WebUtils.getComboBox(Condicoesmoradia.class);
	}

	public List<SelectItem> getListaPeriodoLetivo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		QueryListResult<Periodoletivo> query = QueryManager.GENERIC.allObejctOrdered(Periodoletivo.class)
														   .withOrder("ano desc, semestre desc");
		for (Periodoletivo obj : Repositorio.executeQuery(query)) {
			lista.add(new SelectItem(obj, obj.getAno() + "/"
					+ obj.getSemestre()));
		}
		return lista;
	}

	public List<SelectItem> getListaFolha() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		
		QueryListResult<Folha> query = QueryManager.GENERIC.allObejctOrdered(Folha.class)
												   .withOrder("referencia desc");
		DateFormat format = new SimpleDateFormat("MMM/yyyy");
		
		for (Folha obj :Repositorio.executeQuery(query)) {
			lista.add(new SelectItem(obj, format.format(obj.getReferencia())));
		}

		return lista;
	}

	public List<SelectItem> getListaTipoEvento() {
		return WebUtils.getComboBox(Tipoevento.class);
	}
}
