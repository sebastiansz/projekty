package com.processmonitor.test.jpa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.processmonitor.dao.IFasadaDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/test-runtime-context.xml" })
public class ProcessMonitorJPATests {

	@Autowired
	@Qualifier("fasadaZdalnaDAO")
	private IFasadaDAO service;

	@Test
	public void testSimpleProperties() throws Exception {
		assertNotNull(service);
	}

}
