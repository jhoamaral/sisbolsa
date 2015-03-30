package br.com.repositorio;

import java.util.LinkedHashSet;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.generic.AbstractEntityDomain;

@Stateless
public class PersistenceBean<T extends AbstractEntityDomain> {

	@PersistenceContext
	private EntityManager em;
	
	public synchronized EntityManager getEntityManager() {
		return em;
	}
	
	public synchronized void cadastrar(AbstractEntityDomain obj) throws Exception {
		em.persist(obj);
		em.joinTransaction();
	}
	
	public synchronized void editar(AbstractEntityDomain obj) throws Exception {
		em.merge(obj);
		em.joinTransaction();
	}
	
	public synchronized void delete(Class<? extends AbstractEntityDomain> class1, AbstractEntityDomain obj) throws Exception{
		obj = em.find(class1, String.valueOf(obj));
		em.remove(obj);
		em.joinTransaction();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized LinkedHashSet<T> findSQLCreate(String params) {
		LinkedHashSet<T> resultList = new LinkedHashSet(em.createQuery(params).getResultList());
		return resultList;
	}
}
