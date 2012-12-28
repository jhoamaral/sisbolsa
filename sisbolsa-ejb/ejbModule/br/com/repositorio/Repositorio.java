package br.com.repositorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.repositorio.exceptions.NoRecordFoundException;
import br.com.service.UtilService;

public class Repositorio<T> {

	private List<String> condictions;
	private List<String> joins;
	private List<String> orders;
	private String alias = "o";
	private String select = "o";
	private Class<T> objClass;
	private int limit;
	
	private PersistenceBean<T> persistenceBean;
	
	protected Repositorio() {
		this.condictions = new ArrayList<String>();
		this.joins = new ArrayList<String>();
		this.orders = new ArrayList<String>();	
	}
	
	public EntityManager getEntityManager(){
		return persistenceBean.getEntityManager();
	}
	
	public void setObjClass(Class<T> objClass) {
		this.objClass = objClass;
	}

	public static <T> Repositorio<T> GetInstance(Class<T> objClass){
		Repositorio<T> repo = new Repositorio<T>();
		repo.objClass = objClass;
		try {
			PersistenceBean<T> persistenceBean;
			persistenceBean = InitialContext.doLookup("java:app/sisbolsa-ejb/PersistenceBean");
			repo.setPersistenceBean(persistenceBean);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return repo;
	}
	
	public void setPersistenceBean(PersistenceBean<T> persistenceBean){
		this.persistenceBean = persistenceBean;
	}
	
	public void cadastrar(T obj) throws Exception{
		UtilService.invocaMetodo(obj, "setId", UtilService.generateOid());
		persistenceBean.cadastrar(obj);
	}

	public void editar(T obj) throws Exception{
		persistenceBean.editar(obj);
	}
	
	public void delete(T obj) throws Exception{
		persistenceBean.delete(objClass, obj);
	}

	public LinkedHashSet<T> findSQLCreate(String params) {
		 LinkedHashSet<T> resultList = persistenceBean.findSQLCreate(params);
	     return resultList;
	}

	public void clear(){
		this.condictions = new ArrayList<String>();
		this.orders = new ArrayList<String>();
	}
	
	public void addWhere(String where){
		this.condictions.add(where);
	}
	
	public void addOrder(String order){
		this.orders.add(order);
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void addEquals(Object op1,Object op2){
		this.condictions.add(String.valueOf(op1)+" = '"+String.valueOf(op2)+"'");
	}
	
	public void setAlias(String alias){
		if(this.alias.equals(this.select)){
			this.select = alias;
		}
		this.alias = alias;
	}
	
	public void setSelect(String select){
		this.select = select;
	}
	
	public void join(String join){
		this.joins.add(join);
	}
	
	public String getSql(){
		StringBuilder sb = new StringBuilder();
		sb.append("Select "+this.select);
		sb.append(" From " +this.objClass.getSimpleName()+" "+this.alias);
		
		//================================
		// JOINS
		//================================
		if(!this.joins.isEmpty()){
			for(String join:this.joins){
				sb.append(" "+join );
			}
		}
		
		//================================
		// CLAUSULAS WHERE
		//================================
		
		if(!this.condictions.isEmpty()){
			sb.append(" where");
			for(String clausula:this.condictions){
				sb.append(" "+clausula+" and");
			}
			sb.delete(sb.length() - 4, sb.length());
		}
		
		//================================
		// ORDER BY
		//================================
		if(!this.orders.isEmpty()){
			sb.append(" ORDER BY");
			for(String order:this.orders){
				sb.append(" "+order+",");
			}
			sb.delete(sb.length() - 1, sb.length());
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllList() {
		Query q = persistenceBean.getEntityManager().createQuery(this.getSql());
		//================================
		// LIMIT
		//================================
		if(this.limit > 0){
			q.setMaxResults(this.limit);
		}
		this.clear();
		return q.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public HashSet<T> getAllSet() {
		LinkedHashSet<T> retorno = new LinkedHashSet<T>();
		retorno.addAll( persistenceBean.getEntityManager().createQuery(this.getSql()).getResultList());
		this.clear();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public T getFirstRow() throws NoRecordFoundException{
		List<T> result = this.getAllList();
		if(!result.isEmpty()){
			return (T) result.toArray()[0];
		}else{
			throw new NoRecordFoundException();
		}
	}
	
	public T getById(String id) throws NoRecordFoundException{
		this.clear();
		this.addEquals("id",id);
		return this.getFirstRow();
	}

}
