package br.com.repositorio;

import java.util.LinkedHashSet;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class PersistenceBean<T> {

	@PersistenceContext
	private static EntityManager em;
	
	public  synchronized EntityManager getEntityManager() {
		return em;
	}
	
	public synchronized void cadastrar(T obj) throws Exception {
		em.persist(obj);
		em.joinTransaction();
	}
	
	public synchronized void editar(T obj) throws Exception {
		em.merge(obj);
		em.joinTransaction();
	}
	
	public synchronized void delete(Class<T> objClass, T obj) throws Exception{
		obj = em.find(objClass, String.valueOf(obj));
		em.remove(obj);
		em.joinTransaction();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized LinkedHashSet<T> findSQLCreate(String params) {
		LinkedHashSet<T> resultList = new LinkedHashSet(em.createQuery(params).getResultList());
		return resultList;
	}
}
