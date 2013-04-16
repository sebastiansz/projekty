package com.processmonitor.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fasadaZdalna")
public class FasadaZdalnaDAO implements IFasadaDAO {

	@Autowired
	IExecutorDAO executor;

	@Autowired
	IExecutorDAO executor2;
	
	public FasadaZdalnaDAO() {
		System.out.println(getClass().getSimpleName() + " CTOR()");
		//Thread.dumpStack();
	}

	@PostConstruct
	private void init() {
		System.out.println(getClass().getSimpleName() + ".init() executor:"
				+ executor + ", " + executor2);
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
