package com.processmonitor.test.jpa;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.processmonitor.dao.eee.IFasadaDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/test-runtime-context.xml" })
public class ProcessMonitorJPATests {

	@Autowired
	@Qualifier("fasadaZdalnaDAO")
	private IFasadaDAO service;

	@Autowired
	@Qualifier("processMonitorDS")
	DataSource ds;

	@Test
	public void testSimpleProperties() throws Exception {
		assertNotNull(service);
		assertNotNull(ds);
		
		System.err.println(service);
		System.err.println(ds.getConnection());
		
		
	}

}
