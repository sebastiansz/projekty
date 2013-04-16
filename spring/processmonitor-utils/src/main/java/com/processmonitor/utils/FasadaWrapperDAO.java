package com.processmonitor.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.processmonitor.dao.IFasadaDAO;

@Component
public class FasadaWrapperDAO implements IFasadaWrapperDAO {

	@Autowired
	@Qualifier("fasadaZdalna")
	IFasadaDAO fasada;

	public FasadaWrapperDAO() {
		System.out.println(getClass().getSimpleName() + " CTOR()");
	}

	public IFasadaDAO getFasada() {
		return fasada;
	}

	public void setFasada(IFasadaDAO fasada) {
		this.fasada = fasada;
	}

}
