package br.com.sisbolsa.controller.bolsista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.domain.Cursofaculdade;
import br.com.domain.Endereco;
import br.com.domain.Faculdade;
import br.com.domain.Familiar;
import br.com.domain.Logradouro;
import br.com.domain.Matricula;
import br.com.domain.Pessoa;
import br.com.domain.Pessoaben;
import br.com.domain.Socioeconeomico;
import br.com.domain.Telefone;
import br.com.repositorio.Repositorio;
import br.com.repositorio.querybuilder.QueryManager;
import br.com.repositorio.querybuilder.query.QueryListResult;
import br.com.service.UtilService;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class PessoaController extends AbstractController<Pessoa> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Socioeconeomico socioeconeomicoSelecionado = new Socioeconeomico();
	private Telefone telefoneSelecionado = new Telefone();
	private Endereco enderecoSelecionado = new Endereco();
	private Familiar parenteSelecionado = new Familiar();
	private Pessoaben pessoabenSelecionado = new Pessoaben();
	private Matricula matriculaSelecionado = new Matricula();
	private Faculdade faculdadeSelecionada;

	private List<Logradouro> listLogradouro = new ArrayList<Logradouro>();

	public PessoaController() {
		super(4, "nome");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Telefone> getTelefoneList() {
		if (this.obj instanceof Pessoa) {
			try {
				return new ArrayList(this.obj.getTelefones());
			} catch (Exception e) {
				QueryListResult<Telefone> query = QueryManager.PESSOA.findTelefonesByPessoa()
															  .withPessoa(this.obj);
				Set<Telefone> lista = new LinkedHashSet<Telefone>(Repositorio.executeQuery(query));
				this.obj.setTelefones(lista);
				return new ArrayList(this.obj.getTelefones());
			}
		} else {
			return new ArrayList();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Endereco> getEnderecoList() {
		if (this.obj instanceof Pessoa) {
			try {
				return new ArrayList(this.obj.getEnderecos());
			} catch (Exception e) {
				QueryListResult<Endereco> query = QueryManager.PESSOA.findEnderecosByPessoa()
															  .withPessoa(this.obj);
				
				Set<Endereco> lista = new LinkedHashSet<Endereco>(Repositorio.executeQuery(query));
				this.obj.setEnderecos(lista);
				return new ArrayList(this.obj.getEnderecos());
			}
		} else {
			return new ArrayList();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Familiar> getParenteList() {
		if (this.obj instanceof Pessoa) {
			try {
				return new ArrayList(this.obj.getFamiliares());
			} catch (Exception e) {
				QueryListResult<Familiar> query = QueryManager.PESSOA.findFamiliaresByPessoa()
															  .withPessoa(this.obj);
				Set<Familiar> lista = new LinkedHashSet<Familiar>(Repositorio.executeQuery(query));
				this.obj.setFamiliares(lista);
				return new ArrayList(this.obj.getFamiliares());
			}
		} else {
			return new ArrayList();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Pessoaben> getPessoaBensList() {
		if (this.obj instanceof Pessoa) {
			try {
				return new ArrayList(this.obj.getPessoabens());
			} catch (Exception e) {
				QueryListResult<Pessoaben> query = QueryManager.PESSOA.findPessoaBensByPessoa()
															   .withPessoa(this.obj);
				Set<Pessoaben> lista = new LinkedHashSet<Pessoaben>(Repositorio.executeQuery(query));
				this.obj.setPessoabens(lista);
				return new ArrayList(this.obj.getPessoabens());
			}
		} else {
			return new ArrayList();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Matricula> getMatriculasList() {
		if (this.obj instanceof Pessoa) {
			try {
				return new ArrayList(this.obj.getMatriculas());
			} catch (Exception e) {
				QueryListResult<Matricula> query = QueryManager.PESSOA.findMatriculasByPessoa()
															   .withPessoa(this.obj);
				Set<Matricula> lista = new LinkedHashSet<Matricula>(Repositorio.executeQuery(query));
				this.obj.setMatriculas(lista);
				return new ArrayList(this.obj.getMatriculas());
			}
		} else {
			return new ArrayList();
		}

	}

	public void novo() {
		this.obj = new Pessoa();
		this.telefoneSelecionado = new Telefone();
		this.enderecoSelecionado = new Endereco();
		this.pessoabenSelecionado = new Pessoaben();
		this.parenteSelecionado = new Familiar();
		this.setFiltro("");
	}

	public Socioeconeomico getSocioeconeomicoSelecionado() {
		return socioeconeomicoSelecionado;
	}

	public void setSocioeconeomicoSelecionado(Socioeconeomico socioeconeomicoSelecionado) {
		this.socioeconeomicoSelecionado = socioeconeomicoSelecionado;
	}

	public Telefone getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(Telefone telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	public Familiar getParenteSelecionado() {
		return parenteSelecionado;
	}

	public void setParenteSelecionado(Familiar parenteSelecionado) {
		this.parenteSelecionado = parenteSelecionado;
	}

	public Pessoaben getPessoabenSelecionado() {
		return pessoabenSelecionado;
	}

	public void setPessoabenSelecionado(Pessoaben pessoabenSelecionado) {
		this.pessoabenSelecionado = pessoabenSelecionado;
	}

	public Matricula getMatriculaSelecionado() {
		return matriculaSelecionado;
	}

	public void setMatriculaSelecionado(Matricula matriculaSelecionado) {

		this.matriculaSelecionado = matriculaSelecionado;
		if (this.matriculaSelecionado.getCursofaculdade() instanceof Cursofaculdade) {
			this.faculdadeSelecionada = this.matriculaSelecionado.getCursofaculdade().getFaculdade();
		}
		System.out.println(this.matriculaSelecionado);
	}

	public Faculdade getFaculdadeSelecionada() {
		return faculdadeSelecionada;
	}

	public void setFaculdadeSelecionada(Faculdade faculdadeSelecionada) {
		this.faculdadeSelecionada = faculdadeSelecionada;
	}

	protected String getFieldValue(Pessoa obj, int i) {
		switch (i) {
		case 0:
			return obj.getNome();
		case 1:
			return obj.getCpf().getNumero();
		case 2:
			return obj.getIdentidade().getNumero();
		case 3:
			return obj.getTitulo().getNumero();
		default:
			return "";
		}
	}

	public List<Logradouro> completeLogradouro(String suggest) {
		List<Logradouro> suggestions = new ArrayList<Logradouro>();
		if (this.listLogradouro.isEmpty()) {
			QueryListResult<Logradouro> query = QueryManager.GENERIC.allObejctOrdered(Logradouro.class)
															.withOrder("rua");
			this.listLogradouro = Repositorio.executeQuery(query);
		}
		for (Logradouro obj : this.listLogradouro) {
			if (obj.getRua().toLowerCase().indexOf(suggest.toLowerCase()) > -1 || obj.getBairro().toLowerCase().indexOf(suggest.toLowerCase()) > -1)
				suggestions.add(obj);
		}
		return suggestions;
	}

	public List<SelectItem> getListaFaculdade() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		QueryListResult<Faculdade> query = QueryManager.GENERIC.allObejctOrdered(Faculdade.class)
													   .withOrder("nome");
		for (Faculdade obj : Repositorio.executeQuery(query)) {
			lista.add(new SelectItem(obj, String.valueOf(obj.getNome())));
		}
		return lista;
	}

	public List<SelectItem> getListaCursoFaculdade() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		QueryListResult<Cursofaculdade> query = QueryManager.FACULDADE.findCursoFaculdadeByFaculdade()
												   .withFaculdade(this.getFaculdadeSelecionada());
		for (Cursofaculdade obj : Repositorio.executeQuery(query)) {
			lista.add(new SelectItem(obj, obj.getCurso().getDescricao()));
		}
		return lista;
	}

	public void addTelefone() {
		try {
			this.telefoneSelecionado.setPessoa(this.obj);
			if (this.obj.getId() instanceof String) {
				Repositorio.cadastrar(this.telefoneSelecionado);
			} else {
				this.telefoneSelecionado.setId(UtilService.generateOid());
			}
			this.obj.getTelefones().add(this.telefoneSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.telefoneSelecionado = new Telefone();
	}

	public void removeTelefone() {
		try {
			this.obj.getTelefones().remove(telefoneSelecionado);
			if ((this.obj.getId() instanceof String) && (this.telefoneSelecionado.getId() instanceof String)) {
				Repositorio.delete(this.telefoneSelecionado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.telefoneSelecionado = new Telefone();

	}

	public void configEndereco() {
		this.setEnderecoSelecionado(new Endereco());
	}

	public void addEndereco() {
		try {
			if (!(this.enderecoSelecionado.getId() instanceof String)) {
				this.getEnderecoSelecionado().setPessoa(this.obj);
				if (this.obj.getId() instanceof String) {
					Repositorio.cadastrar(this.getEnderecoSelecionado());
				} else {
					this.getEnderecoSelecionado().setId(UtilService.generateOid());
				}
				this.obj.getEnderecos().add(this.getEnderecoSelecionado());
			} else {
				Repositorio.editar(this.getEnderecoSelecionado());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setEnderecoSelecionado(new Endereco());

	}

	public void removeEndereco() {
		try {
			this.obj.getEnderecos().remove(this.enderecoSelecionado);
			if ((this.obj.getId() instanceof String) && (this.getEnderecoSelecionado().getId() instanceof String)) {
				Repositorio.delete(this.getEnderecoSelecionado());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setEnderecoSelecionado(new Endereco());
	}

	public void configParente() {
		this.setParenteSelecionado(new Familiar());
	}

	public void addParentesco() {
		try {
			if (!(this.parenteSelecionado.getId() instanceof String)) {
				this.parenteSelecionado.setPessoa(this.obj);
				if (this.obj.getId() instanceof String) {
					Repositorio.cadastrar(this.parenteSelecionado);
				} else {
					this.parenteSelecionado.setId(UtilService.generateOid());
				}
				this.obj.getFamiliares().add(this.parenteSelecionado);
			} else {
				Repositorio.editar(this.parenteSelecionado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.parenteSelecionado = new Familiar();
	}

	public void removeParente() {
		try {
			this.obj.getFamiliares().remove(parenteSelecionado);
			if ((this.obj.getId() instanceof String) && (this.parenteSelecionado.getId() instanceof String)) {
				Repositorio.delete(this.parenteSelecionado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.parenteSelecionado = new Familiar();
	}

	public void configMatricula() {
		this.setMatriculaSelecionado(new Matricula());
		this.faculdadeSelecionada = null;
	}

	public void addMatricula() {
		try {
			if (!(this.matriculaSelecionado.getId() instanceof String)) {
				this.getMatriculaSelecionado().setPessoa(this.obj);
				if (this.obj.getId() instanceof String) {
					Repositorio.cadastrar(this.getMatriculaSelecionado());
				} else {
					this.getMatriculaSelecionado().setId(UtilService.generateOid());
				}
				this.obj.getMatriculas().add(this.getMatriculaSelecionado());
			} else {
				Repositorio.editar(this.matriculaSelecionado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMatriculaSelecionado(new Matricula());
		this.setFaculdadeSelecionada(new Faculdade());
	}

	public void removeMatricula() {
		try {
			this.obj.getMatriculas().remove(getMatriculaSelecionado());
			if ((this.obj.getId() instanceof String) && (this.getMatriculaSelecionado().getId() instanceof String)) {
				Repositorio.delete(this.getMatriculaSelecionado());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMatriculaSelecionado(new Matricula());
	}

	public void addBen() {
		try {
			this.pessoabenSelecionado.setPessoa(this.obj);
			if (this.obj.getId() instanceof String) {
				Repositorio.cadastrar(this.pessoabenSelecionado);
			} else {
				this.pessoabenSelecionado.setId(UtilService.generateOid());
			}
			this.obj.getPessoabens().add(this.pessoabenSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.pessoabenSelecionado = new Pessoaben();

	}

	public void removeBen() {
		try {
			this.obj.getPessoabens().remove(pessoabenSelecionado);
			if ((this.obj.getId() instanceof String) && (this.pessoabenSelecionado.getId() instanceof String)) {
				Repositorio.delete(this.pessoabenSelecionado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.pessoabenSelecionado = new Pessoaben();
	}

	public void salvar() {
		super.salvar();
		this.novo();
	}

}
