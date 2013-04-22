package com.processmonitor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.processmonitor.jpa.AbstractJPA;

public class GenericDaoJpa<T extends AbstractJPA> implements GenericDao<T> {

	final Class<T> type;

	@PersistenceContext
	protected EntityManager em;

	public GenericDaoJpa(Class<T> type) {
		super();
		this.type = type;
	}

	@Transactional(readOnly = true)
	public T get(Long pId) {
		if (pId == null) {
			return null;
		}
		return em.find(type, pId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> getAll() {
		return em.createQuery("select ob from " + type.getName() + " ob ").getResultList();
	}

	public void save(T pEntity) {
		em.persist(pEntity);
	}

	public T merge(T pEntity) {
		return em.merge(pEntity);
	}

	public void delete(T pEntity) {
		em.remove(pEntity);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
