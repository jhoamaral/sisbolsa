package br.com.sisbolsa.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.convert.Converter;

import br.com.domain.Ben;
import br.com.domain.Boleto;
import br.com.domain.Condicoesmoradia;
import br.com.domain.Cursofaculdade;
import br.com.domain.Escolaridade;
import br.com.domain.Estadocivil;
import br.com.domain.Evento;
import br.com.domain.Faculdade;
import br.com.domain.Folha;
import br.com.domain.Item;
import br.com.domain.Logradouro;
import br.com.domain.Matricula;
import br.com.domain.Matriculaperiodo;
import br.com.domain.Periodoletivo;
import br.com.domain.Pessoa;
import br.com.domain.Situacaojuridica;
import br.com.domain.Situacaooperacional;
import br.com.domain.Tipoevento;
import br.com.domain.Tipoparentesco;
import br.com.domain.Tipotelefone;
import br.com.sisbolsa.converter.EntityConverter;

@ManagedBean
@SessionScoped
public class ConverterController {
	
	public Converter getEstadoCivilConverter() {
		return EntityConverter.GetInstance(Estadocivil.class);
	}

	public Converter getEscolaridadeConverter() {
		return EntityConverter.GetInstance(Escolaridade.class);
	}

	public Converter getSituacaoJuridicaConverter() {
		return EntityConverter.GetInstance(Situacaojuridica.class);
	}

	public Converter getSituacaoOperacionalConverter() {
		return EntityConverter.GetInstance(Situacaooperacional.class);
	}

	public Converter getCondicoesMoradiaConverter() {
		return EntityConverter.GetInstance(Condicoesmoradia.class);
	}

	public Converter getTipoTelefoneConverter() {
		return EntityConverter.GetInstance(Tipotelefone.class);
	}
	
	public Converter getLogradouroConverter() {
		return EntityConverter.GetInstance(Logradouro.class);
	}
	
	public Converter getTipoParentescoConverter() {
		return EntityConverter.GetInstance(Tipoparentesco.class);
	}
	
	public Converter getBenConverter() {
		return EntityConverter.GetInstance(Ben.class);
	}
	
	public Converter getFaculdadeConverter() {
		return EntityConverter.GetInstance(Faculdade.class);
	}
	
	public Converter getCursoFaculdadeConverter() {
		return EntityConverter.GetInstance(Cursofaculdade.class);
	}
	
	public Converter getPessoaConverter() {
		return EntityConverter.GetInstance(Pessoa.class);
	}
	
	public Converter getPeriodoLetivoConverter() {
		return EntityConverter.GetInstance(Periodoletivo.class);
	}
	
	public Converter getTipoEventoConverter() {
		return EntityConverter.GetInstance(Tipoevento.class);
	}
	
	public Converter getEventoConverter() {
		return EntityConverter.GetInstance(Evento.class);
	}
	
	public Converter getItemConverter() {
		return EntityConverter.GetInstance(Item.class);
	}
	
	public Converter getMatriculaConverter(){
		return EntityConverter.GetInstance(Matricula.class);
	}
	
	public Converter getMatriculaPeriodoConverter(){
		return EntityConverter.GetInstance(Matriculaperiodo.class);
	}
	
	public Converter getBoletoConverter(){
		return EntityConverter.GetInstance(Boleto.class);
	}
	
	public Converter getFolhaConverter(){
		return EntityConverter.GetInstance(Folha.class);
	}
}
