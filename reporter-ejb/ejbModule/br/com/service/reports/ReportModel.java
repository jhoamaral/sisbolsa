package br.com.service.reports;

import java.io.InputStream;

public enum ReportModel {
	 FICHA_CADASTRO("ficha_cadastro.jasper"),
	 FOLHA_DE_PAGAMENTOS("folha_de_pagamento.jasper"),
	 RECIBO_DE_BOLETOS("recibo_boletos.jasper"),
	 ATIVOS_POR_CURSO("ativos_por_curso.jasper"),
	 CONFERENCIA_FOLHA("conferencia_folha.jasper"),
	 POSSIVEIS_FORMANDOS("possiveis_formandos.jasper"),
	 DECLARACAO_RENDIMENTOS("declaracao_rendimentos.jasper");
	
	String path;
	
	private ReportModel(String path){
		this.path = path;
	}
	
	public InputStream value(){
		return getClass().getResourceAsStream(path);
	}
	
	
}
