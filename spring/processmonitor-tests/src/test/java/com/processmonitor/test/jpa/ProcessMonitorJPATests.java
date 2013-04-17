package com.processmonitor.test.jpa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.processmonitor.utils.IFasadaWrapperDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/persistence-context.xml",
		"classpath*:META-INF/spring/services-context.xml", "classpath:META-INF/spring/test-app-context.xml" })
public class ProcessMonitorJPATests {

	@Autowired
	private IFasadaWrapperDAO service;

	@Test
	public void testSimpleProperties() throws Exception {
		assertNotNull(service);
	}

}
