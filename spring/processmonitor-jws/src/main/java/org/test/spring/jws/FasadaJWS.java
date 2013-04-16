package org.test.spring.jws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.spring.dao.IFasadaDAO;

@Component
public class FasadaJWS implements IFasadaJWS {

	@Autowired
	IFasadaDAO dao;

	public FasadaJWS() {
		System.out.println(getClass().getSimpleName() + " CTOR()");
	}

}
