package br.com.repositorio;

import java.util.LinkedHashSet;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
public class PersistenceBean<T> {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}

	public void cadastrar(T obj) throws Exception {
		em.persist(obj);
		em.joinTransaction();
	}

	public void editar(T obj) throws Exception {
		em.merge(obj);
		em.joinTransaction();
	}

	public void delete(Class<T> objClass, T obj) throws Exception{
		obj = em.find(objClass, String.valueOf(obj));
		em.remove(obj);
		em.joinTransaction();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LinkedHashSet<T> findSQLCreate(String params) {
		LinkedHashSet<T> resultList = new LinkedHashSet(em.createQuery(params).getResultList());
		return resultList;
	}
}
