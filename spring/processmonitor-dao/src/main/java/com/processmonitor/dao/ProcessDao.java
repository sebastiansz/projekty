package com.processmonitor.dao;

import org.springframework.stereotype.Repository;

import com.processmonitor.jpa.ProcessEntity;

@Repository("processDao")
public class ProcessDao extends GenericDaoJpa<ProcessEntity> {

	public ProcessDao() {
		super(ProcessEntity.class);
	}

}
