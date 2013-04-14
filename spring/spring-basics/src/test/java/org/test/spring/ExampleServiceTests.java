package org.test.spring;

import junit.framework.TestCase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.IExternalExecutorDAO;

public class ExampleServiceTests extends TestCase {

	public void off_testXml() throws Exception {
		org.springframework.context.ApplicationContext pAppContext = new ClassPathXmlApplicationContext(
				"/META-INF/spring/app-context.xml");
		IFasadaDAO pBean1 = (IFasadaDAO) pAppContext.getBean("fasada1");
		assertTrue(pBean1 != null);
		assertTrue(pBean1.getExecutor() != null);

		System.out.println(pBean1);
	}

	public void OFF_testAnnotations() throws Exception {
		org.springframework.context.ApplicationContext pAppContext = new AnnotationConfigApplicationContext(
				"org.test.spring");
		IFasadaDAO pBean1 = (IFasadaDAO) pAppContext.getBean(IFasadaDAO.class);
		System.out.println(pBean1);
	}

	public void testMerge() throws Exception {
		org.springframework.context.ConfigurableApplicationContext pAnnotations = new AnnotationConfigApplicationContext(
				"org.test.spring");
		org.springframework.context.ConfigurableApplicationContext pXML = new ClassPathXmlApplicationContext(
				"/META-INF/spring/app-context.xml");

		pAnnotations.setParent(pXML);

		IFasadaDAO pBean1 = (IFasadaDAO) pAnnotations.getBean(IFasadaDAO.class);
		pAnnotations.getBean(IExternalExecutorDAO.class);
		System.out.println(pBean1);
	}

}
