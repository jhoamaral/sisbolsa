package br.com.repositorio.querybuilder;

import br.com.repositorio.querybuilder.query.boleto.FindBoletoByAtivobolsa;
import br.com.repositorio.querybuilder.query.boleto.FindBoletoByData;
import br.com.repositorio.querybuilder.query.boleto.FindUltimoBoleto;
import br.com.repositorio.querybuilder.query.bolsista.FindMatriculaLikeCursoLikeInstituicaoLikePeriodo;
import br.com.repositorio.querybuilder.query.bolsista.FindUltimoSocioEconomico;
import br.com.repositorio.querybuilder.query.curso.FindCursoLikeInstituicao;
import br.com.repositorio.querybuilder.query.evento.FindEventoCalculadoByItem;
import br.com.repositorio.querybuilder.query.evento.FindHistoricoValorByEvento;
import br.com.repositorio.querybuilder.query.evento.FindUltimoHistoricoValor;
import br.com.repositorio.querybuilder.query.faculdade.FindCursoInstituicaoByInstituicao;
import br.com.repositorio.querybuilder.query.folha.FindAtivobolsaByPeriodoLetivoOrderedByCurso;
import br.com.repositorio.querybuilder.query.folha.FindAtivobolsaNotInFolha;
import br.com.repositorio.querybuilder.query.folha.FindFolhasAbertas;
import br.com.repositorio.querybuilder.query.generic.AllObjects;
import br.com.repositorio.querybuilder.query.generic.AllObjectsOrdered;
import br.com.repositorio.querybuilder.query.generic.FindById;
import br.com.repositorio.querybuilder.query.item.FindItemByAno;
import br.com.repositorio.querybuilder.query.item.FindItemByAtivobolsa;
import br.com.repositorio.querybuilder.query.item.FindItemByAtivobolsaAndFolha;
import br.com.repositorio.querybuilder.query.item.FindItemByFolha;
import br.com.repositorio.querybuilder.query.item.FindItensWithCollectionsByFolha;
import br.com.repositorio.querybuilder.query.menu.MenusFilhos;
import br.com.repositorio.querybuilder.query.menu.MenusPrincipais;
import br.com.repositorio.querybuilder.query.periodoletivo.FindByAnoSemestre;
import br.com.repositorio.querybuilder.query.pessoa.FindAtivobolsaByPeriodoLetivo;
import br.com.repositorio.querybuilder.query.pessoa.FindAtivobolsaWithCollections;
import br.com.repositorio.querybuilder.query.pessoa.FindEnderecosByPessoa;
import br.com.repositorio.querybuilder.query.pessoa.FindFamiliaresByPessoa;
import br.com.repositorio.querybuilder.query.pessoa.FindMatriculasByPessoa;
import br.com.repositorio.querybuilder.query.pessoa.FindPessoaBensByPessoa;
import br.com.repositorio.querybuilder.query.pessoa.FindPrimeiroEndereco;
import br.com.repositorio.querybuilder.query.pessoa.FindTelefonesByPessoa;
import br.com.repositorio.querybuilder.query.usuario.FindByLogin;

public abstract class QueryManager {

	// ============================
	// USUARIO
	// ============================
	public static class USUARIO {
		public static FindByLogin findByLogin() {
			return new FindByLogin();
		}
	}

	// ============================
	// GENERIC
	// ============================
	public static class GENERIC {
		public static <T> AllObjects<T> allObejct(Class<T> type) {
			return new AllObjects<T>().setObject(type);
		}

		public static <T> AllObjectsOrdered<T> allObejctOrdered(Class<T> type) {
			return new AllObjectsOrdered<T>().setObject(type);
		}
		
		public static <T> FindById<T> findbyId(Class<T> type) {
			return new FindById<T>().setObject(type);
		}
	}

	// ============================
	// PESSOA
	// ============================
	public static class PESSOA {
		public static FindMatriculasByPessoa findMatriculasByPessoa() {
			return new FindMatriculasByPessoa();
		}
		
		public static FindPessoaBensByPessoa findPessoaBensByPessoa() {
			return new FindPessoaBensByPessoa();
		}
		
		public static FindPrimeiroEndereco findPrimeiroEndereco() {
			return new FindPrimeiroEndereco();
		}
		
		public static FindEnderecosByPessoa findEnderecosByPessoa() {
			return new FindEnderecosByPessoa();
		}
		
		public static FindTelefonesByPessoa findTelefonesByPessoa() {
			return new FindTelefonesByPessoa();
		}
		
		public static FindFamiliaresByPessoa findFamiliaresByPessoa() {
			return new FindFamiliaresByPessoa();
		}
		
		public static FindAtivobolsaWithCollections findMatriculaPeriodoWithCollections() {
			return new FindAtivobolsaWithCollections();
		}
		
		public static FindAtivobolsaByPeriodoLetivo findMatriculaPeriodoByPeriodoLetivo() {
			return new FindAtivobolsaByPeriodoLetivo();
		}
	}

	// ============================
	// MENU
	// ============================
	public static class MENU {
		public static MenusPrincipais menusPrincipais() {
			return new MenusPrincipais();
		}

		public static MenusFilhos menusFilhos() {
			return new MenusFilhos();
		}
	}

	// ============================
	// PERIODOLETIVO
	// ============================
	public static class PERIODOLETIVO {
		public static FindByAnoSemestre findByAnoSemestre() {
			return new FindByAnoSemestre();
		}
	}

	// ============================
	// EVENTO
	// ============================
	public static class EVENTO {
		public static FindUltimoHistoricoValor findUltimoHistoricoEvento() {
			return new FindUltimoHistoricoValor();
		}

		public static FindHistoricoValorByEvento findHistoricoValorByEvento() {
			return new FindHistoricoValorByEvento();
		}
		
		public static FindEventoCalculadoByItem findEventoCalculadoByItem() {
			return new FindEventoCalculadoByItem();
		}
	}

	// ============================
	// BOLSISTA
	// ============================
	public static class BOLSISTA {
		public static FindUltimoSocioEconomico findUltimoSocioEconomico() {
			return new FindUltimoSocioEconomico();
		}
		
		public static FindMatriculaLikeCursoLikeInstituicaoLikePeriodo findMatriculaLikeCursoLikeInstituicaoLikePeriodo() {
			return new FindMatriculaLikeCursoLikeInstituicaoLikePeriodo();
		}
	}
	// ============================
	// BOLETO
	// ============================
	public static class BOLETO {
		public static FindUltimoBoleto findUltimoBoleto() {
			return new FindUltimoBoleto();
		}

		public static FindBoletoByData findBoletoByData() {
			return new FindBoletoByData();
		}

		public static FindBoletoByAtivobolsa findBoletoByAtivobolsa() {
			return new FindBoletoByAtivobolsa();
		}
	}

	// ============================
	// FACULDADE
	// ============================
	public static class FACULDADE {
		public static FindCursoInstituicaoByInstituicao findCursoInstituicaoByInstituicao() {
			return new FindCursoInstituicaoByInstituicao();
		}
	}
	
	// ============================
	// FACULDADE
	// ============================
	public static class CURSO {
		public static FindCursoLikeInstituicao findCursoLikeInstituicao() {
			return new FindCursoLikeInstituicao();
		}
	}

	// ============================
	// ITEM
	// ============================
	public static class ITEM {
		public static FindItemByAtivobolsa findItemByAtivobolsa() {
			return new FindItemByAtivobolsa();
		}
		
		public static FindItemByFolha findItemByFolha() {
			return new FindItemByFolha();
		}
		
		public static FindItemByAtivobolsaAndFolha findItemByAtivobolsaAndFolha() {
			return new FindItemByAtivobolsaAndFolha();
		}
		
		public static FindItemByAno findItemByAno() {
			return new FindItemByAno();
		}
	}
	
	// ============================
	// FOLHA
	// ============================
	public static class FOLHA {
		public static FindFolhasAbertas findFolhasAbertas() {
			return new FindFolhasAbertas();
		}
		
		public static FindItensWithCollectionsByFolha findFolhasWithCollections() {
			return new FindItensWithCollectionsByFolha();
		}
		
		public static FindAtivobolsaNotInFolha findAtivobolsaNotInFolha() {
			return new FindAtivobolsaNotInFolha();
		}
		
		public static FindAtivobolsaByPeriodoLetivoOrderedByCurso findMatriculaPeriodoByPeriodoLetivoOrderedByCurso() {
			return new FindAtivobolsaByPeriodoLetivoOrderedByCurso();
		}
	}

}
