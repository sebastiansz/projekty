package com.processmonitor.jws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.processmonitor.dao.eee.IFasadaDAO;

@Component
public class FasadaJWS implements IFasadaJWS {

	@Autowired
	@Qualifier("fasadaZdalna")
	IFasadaDAO dao;

	public FasadaJWS() {
		System.out.println(getClass().getSimpleName() + " CTOR()");
	}

}
