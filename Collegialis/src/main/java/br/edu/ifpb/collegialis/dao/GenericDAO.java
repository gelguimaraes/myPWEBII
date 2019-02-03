package br.edu.ifpb.collegialis.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

public class GenericDAO<T, PK extends Serializable> implements IGenericDAO<T, PK> {

	protected Class<T> entityClass;

	protected EntityManager entityManager;
	
	
	public GenericDAO(EntityManager em) {
		this();
		this.entityManager = em;
	}

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T insert(T t) {
		this.entityManager.persist(t);
		return t;
	}

	@Override
	public T find(PK id) {
		return this.entityManager.find(entityClass, id);
	}

	@Override
	public T update(T t) {
		return this.entityManager.merge(t);
	}

	@Override
	public void delete(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}
	
	@Override
	public void refresh(T t) {
		this.entityManager.refresh(t);
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public void beginTransaction() {
		this.entityManager.getTransaction().begin();
		
	}

	@Override
	public void commit() {
		this.entityManager.getTransaction().commit();
	}

	@Override
	public void rollback() {
		this.entityManager.getTransaction().rollback();		
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
