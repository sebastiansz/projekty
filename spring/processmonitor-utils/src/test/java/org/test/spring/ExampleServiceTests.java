package org.test.spring;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.processmonitor.utils.IFasadaWrapperDAO;

public class ExampleServiceTests extends TestCase {

	// public void testMultiJar() throws Exception {
	// AnnotationConfigApplicationContext pContext = new AnnotationConfigApplicationContext(
	// "org.test.spring.dao", "org.test.spring.jws");
	// System.out.println(pContext);
	// }

	public void testBootstrapXml() throws Exception {
		org.springframework.context.ApplicationContext pAppContext = new ClassPathXmlApplicationContext(
				"classpath*:META-INF/spring/app-context.xml");
		//IFasadaWrapperDAO pBean1 = pAppContext.getBean(IFasadaWrapperDAO.class);
		//assertTrue(pBean1 != null);

		//System.out.println(pBean1);
	}

	// public void OFF_testAnnotations() throws Exception {
	// org.springframework.context.ApplicationContext pAppContext = new AnnotationConfigApplicationContext(
	// "org.test.spring");
	// IFasadaWrapperDAO pBean1 = (IFasadaWrapperDAO) pAppContext.getBean(IFasadaWrapperDAO.class);
	// System.out.println(pBean1);
	// }
	//
	// public void testMerge() throws Exception {
	// org.springframework.context.ConfigurableApplicationContext pAnnotations = new AnnotationConfigApplicationContext(
	// "org.test.spring");
	// org.springframework.context.ConfigurableApplicationContext pXML = new ClassPathXmlApplicationContext(
	// "/META-INF/spring/app-context.xml");
	//
	// pAnnotations.setParent(pXML);
	//
	// IFasadaWrapperDAO pBean1 = (IFasadaWrapperDAO) pAnnotations.getBean(IFasadaWrapperDAO.class);
	// System.out.println(pBean1);
	// }

}
