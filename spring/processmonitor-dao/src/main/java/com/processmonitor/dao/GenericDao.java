package com.processmonitor.dao;

import java.util.List;

import com.processmonitor.jpa.AbstractJPA;

public interface GenericDao<T extends AbstractJPA> {

	T get(Long pId);

	List<T> getAll();

	void save(T pEntity);

	void delete(T pEntity);

}
