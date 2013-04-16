package org.test.spring.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FasadaDAO implements IFasadaDAO {

	@Autowired
	IExecutorDAO executor;

	@Autowired
	IExecutorDAO executor2;
	
	public FasadaDAO() {
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
