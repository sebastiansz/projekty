package com.processmonitor.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.processmonitor.jpa.ProcessEntity;

@Component
@Qualifier("fasadaZdalna")
public class FasadaZdalnaDAO implements IFasadaDAO {

	@PersistenceContext(name = "processMonitorPersistenceUnit2")
	EntityManager em;

	@Autowired
	IExecutorDAO executor;

	@Autowired
	IExecutorDAO executor2;

	public FasadaZdalnaDAO() {
		System.out.println(getClass().getSimpleName() + " CTOR()");
		// Thread.dumpStack();
	}

	@PostConstruct
	private void init() {
		System.out.println(getClass().getSimpleName() + ".init() executor:" + executor + ", " + executor2 + ", em:"
				+ em);
		ProcessEntity pProcess = em.find(ProcessEntity.class, 2720838L);
		System.err.println("GEFUNDEN ENTITY: " + pProcess);
	}

	@PreDestroy
	private void destroy() {
		System.out.println(getClass().getSimpleName() + ".destroy()");

	}

	public IExecutorDAO getExecutor() {
		return executor;
	}

	public void setExecutor(IExecutorDAO executor) {
		this.executor = executor;
	}

}
