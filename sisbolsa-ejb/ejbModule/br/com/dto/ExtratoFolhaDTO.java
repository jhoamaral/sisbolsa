package br.com.dto;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.domain.Item;

public class ExtratoFolhaDTO {
	
	private Item item;
	private String valor;
	private String mes;
	private String nome;
	private String cpf;
	private String instituicao;
	private String curso;
	private String dataConcessao;
	private String dataDeposito;
	
	
	
	public ExtratoFolhaDTO(Item item) {
		this.item = item;
		this.nome = item.getAtivobolsa().getMatricula().getPessoa().getNome();
		this.cpf = item.getAtivobolsa().getMatricula().getPessoa().getCpf().getNumero();
		DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		this.dataConcessao = formater.format(item.getAtivobolsa().getMatricula().getDataIngresso());
		if(item.getFolha().getDataDeposito() instanceof Date){
			this.dataDeposito = formater.format(item.getFolha().getDataDeposito());
		}else{
			this.dataDeposito = "";
		}
		
		this.instituicao= item.getAtivobolsa().getMatricula().getCursoinstituicao().getInstituicao().getNome();
		this.curso = item.getAtivobolsa().getMatricula().getCursoinstituicao().getCurso().getDescricao();
		DateFormat formaterMes = new SimpleDateFormat("MMMM");
		this.mes = formaterMes.format(item.getFolha().getReferencia());
		
		NumberFormat formatador = NumberFormat.getNumberInstance();
		formatador.setGroupingUsed(true);
		this.valor =  formatador.format(item.getValor());
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDataConcessao() {
		return dataConcessao;
	}

	public void setDataConcessao(String dataConcessao) {
		this.dataConcessao = dataConcessao;
	}

	public String getDataDeposito() {
		return dataDeposito;
	}

	public void setDataDeposito(String dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public static List<ExtratoFolhaDTO> getList(List<Item> items){
		List<ExtratoFolhaDTO> retorno = new ArrayList<ExtratoFolhaDTO>();
		System.out.println("Total de items"+items.size() );
		for(Item item:items){
			retorno.add(new ExtratoFolhaDTO(item));
		}
		
		return retorno;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return this.item.equals(arg0);
	}
	
	@Override
	public int hashCode() {
		return this.item.hashCode();
	}
	
}
