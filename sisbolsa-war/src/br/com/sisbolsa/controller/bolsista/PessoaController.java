package br.com.sisbolsa.controller.bolsista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import br.com.service.UtilService;
import br.com.sisbolsa.controller.AbstractController;

@ManagedBean
@ViewScoped
public class PessoaController extends AbstractController<Pessoa> implements
		Serializable {

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
		super(Repositorio.GetInstance(Pessoa.class), 4, "nome");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Telefone> getTelefoneList() {
		if (this.obj instanceof Pessoa) {
			try {
				return new ArrayList(this.obj.getTelefones());
			} catch (Exception e) {
				Repositorio<Telefone> getTelefones = Repositorio
						.GetInstance(Telefone.class);
				getTelefones.addEquals("pessoa", this.obj);
				this.obj.setTelefones(getTelefones.getAllSet());
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
				Repositorio<Endereco> getEnderecos = Repositorio
						.GetInstance(Endereco.class);
				getEnderecos.addEquals("pessoa", this.obj);
				this.obj.setEnderecos(getEnderecos.getAllSet());
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
				Repositorio<Familiar> getObjects = Repositorio
						.GetInstance(Familiar.class);
				getObjects.addEquals("pessoa", this.obj);
				this.obj.setFamiliares(getObjects.getAllSet());
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
				Repositorio<Pessoaben> getObjects = Repositorio
						.GetInstance(Pessoaben.class);
				getObjects.addEquals("pessoa", this.obj);
				this.obj.setPessoabens(getObjects.getAllSet());
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
				Repositorio<Matricula> getObjects = Repositorio
						.GetInstance(Matricula.class);
				getObjects.addEquals("pessoa", this.obj);
				this.obj.setMatriculas(getObjects.getAllSet());
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

	public void setSocioeconeomicoSelecionado(
			Socioeconeomico socioeconeomicoSelecionado) {
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
			this.faculdadeSelecionada = this.matriculaSelecionado
					.getCursofaculdade().getFaculdade();
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

	public List<Logradouro> completeLogradouro(String query) {
		List<Logradouro> suggestions = new ArrayList<Logradouro>();

		if (this.listLogradouro.isEmpty()) {
			Repositorio<Logradouro> getLogradouros = Repositorio
					.GetInstance(Logradouro.class);
			getLogradouros.addOrder("rua");
			this.listLogradouro = getLogradouros.getAllList();
		}

		for (Logradouro obj : this.listLogradouro) {
			if (obj.getRua().toLowerCase().startsWith(query.toLowerCase())
					|| obj.getBairro().toLowerCase()
							.startsWith(query.toLowerCase()))
				suggestions.add(obj);
		}

		return suggestions;
	}

	public List<SelectItem> getListaFaculdade() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		Repositorio<Faculdade> getObj = Repositorio
				.GetInstance(Faculdade.class);
		getObj.addOrder("nome");
		for (Faculdade obj : getObj.getAllList()) {
			lista.add(new SelectItem(obj, String.valueOf(obj.getNome())));
		}

		return lista;
	}

	public List<SelectItem> getListaCursoFaculdade() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		Repositorio<Cursofaculdade> getObj = Repositorio
				.GetInstance(Cursofaculdade.class);
		getObj.addEquals("faculdade", this.getFaculdadeSelecionada());
		getObj.addOrder("curso.descricao");
		for (Cursofaculdade obj : getObj.getAllList()) {
			lista.add(new SelectItem(obj, obj.getCurso().getDescricao()));
		}

		return lista;
	}

	public void addTelefone() {
		try {
			this.telefoneSelecionado.setPessoa(this.obj);
			if (this.obj.getId() instanceof String) {
				Repositorio<Telefone> saveTel = Repositorio
						.GetInstance(Telefone.class);
				saveTel.cadastrar(this.telefoneSelecionado);

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
			if ((this.obj.getId() instanceof String)
					&& (this.telefoneSelecionado.getId() instanceof String)) {
				Repositorio<Telefone> saveTel = Repositorio
						.GetInstance(Telefone.class);
				saveTel.delete(this.telefoneSelecionado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.telefoneSelecionado = new Telefone();
		System.out.println("Telefone removido!");

	}

	public void addEndereco() {
		try {
			this.getEnderecoSelecionado().setPessoa(this.obj);
			if (this.obj.getId() instanceof String) {
				Repositorio<Endereco> saveTel = Repositorio
						.GetInstance(Endereco.class);
				saveTel.cadastrar(this.getEnderecoSelecionado());

			} else {
				this.getEnderecoSelecionado().setId(UtilService.generateOid());
			}
			this.obj.getEnderecos().add(this.getEnderecoSelecionado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setEnderecoSelecionado(new Endereco());

	}

	public void removeEndereco() {

		try {
			System.out.println(this.obj.getEnderecos().remove(
					this.enderecoSelecionado));
			if ((this.obj.getId() instanceof String)
					&& (this.getEnderecoSelecionado().getId() instanceof String)) {
				Repositorio<Endereco> saveTel = Repositorio
						.GetInstance(Endereco.class);
				saveTel.delete(this.getEnderecoSelecionado());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setEnderecoSelecionado(new Endereco());

	}

	public void addParentesco() {
		try {
			this.parenteSelecionado.setPessoa(this.obj);
			if (this.obj.getId() instanceof String) {
				Repositorio<Familiar> saveTel = Repositorio
						.GetInstance(Familiar.class);
				saveTel.cadastrar(this.parenteSelecionado);

			} else {
				this.parenteSelecionado.setId(UtilService.generateOid());
			}
			this.obj.getFamiliares().add(this.parenteSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.parenteSelecionado = new Familiar();

	}

	public void removeParente() {
		try {
			this.obj.getFamiliares().remove(parenteSelecionado);
			if ((this.obj.getId() instanceof String)
					&& (this.parenteSelecionado.getId() instanceof String)) {
				Repositorio<Familiar> saveTel = Repositorio
						.GetInstance(Familiar.class);
				saveTel.delete(this.parenteSelecionado);
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
					Repositorio<Matricula> saveTel = Repositorio
							.GetInstance(Matricula.class);
					saveTel.cadastrar(this.getMatriculaSelecionado());
				} else {
					this.getMatriculaSelecionado().setId(
							UtilService.generateOid());
				}
				this.obj.getMatriculas().add(this.getMatriculaSelecionado());
			} else {
				Repositorio<Matricula> saveMatricula = Repositorio
						.GetInstance(Matricula.class);
				saveMatricula.editar(this.matriculaSelecionado);
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
			if ((this.obj.getId() instanceof String)
					&& (this.getMatriculaSelecionado().getId() instanceof String)) {
				Repositorio<Matricula> saveTel = Repositorio
						.GetInstance(Matricula.class);
				saveTel.delete(this.getMatriculaSelecionado());
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
				Repositorio<Pessoaben> saveTel = Repositorio
						.GetInstance(Pessoaben.class);
				saveTel.cadastrar(this.pessoabenSelecionado);

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
			if ((this.obj.getId() instanceof String)
					&& (this.pessoabenSelecionado.getId() instanceof String)) {
				Repositorio<Pessoaben> saveTel = Repositorio
						.GetInstance(Pessoaben.class);
				saveTel.delete(this.pessoabenSelecionado);
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
