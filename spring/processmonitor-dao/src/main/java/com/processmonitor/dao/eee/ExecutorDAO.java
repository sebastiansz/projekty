package com.processmonitor.dao.eee;

import org.springframework.stereotype.Component;

@Component
public class ExecutorDAO implements IExecutorDAO {

	public ExecutorDAO() {
		System.out.println(getClass().getSimpleName() + " CTOR()");

	}
}
