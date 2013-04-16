package org.test.spring;

import junit.framework.TestCase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExampleServiceTests extends TestCase {

	public void testMultiJar() throws Exception {
		AnnotationConfigApplicationContext pContext = new AnnotationConfigApplicationContext(
				"org.test.spring.dao", "org.test.spring.jws");
		System.out.println(pContext);
	}

	// public void off_testXml() throws Exception {
	// org.springframework.context.ApplicationContext pAppContext = new
	// ClassPathXmlApplicationContext(
	// "/META-INF/spring/app-context.xml");
	// IFasadaWrapperDAO pBean1 = (IFasadaWrapperDAO)
	// pAppContext.getBean("fasada1");
	// assertTrue(pBean1 != null);
	// assertTrue(pBean1.getExecutor() != null);
	//
	// System.out.println(pBean1);
	// }
	//
	// public void OFF_testAnnotations() throws Exception {
	// org.springframework.context.ApplicationContext pAppContext = new
	// AnnotationConfigApplicationContext(
	// "org.test.spring");
	// IFasadaWrapperDAO pBean1 = (IFasadaWrapperDAO)
	// pAppContext.getBean(IFasadaWrapperDAO.class);
	// System.out.println(pBean1);
	// }
	//
	// public void testMerge() throws Exception {
	// org.springframework.context.ConfigurableApplicationContext pAnnotations =
	// new AnnotationConfigApplicationContext(
	// "org.test.spring");
	// org.springframework.context.ConfigurableApplicationContext pXML = new
	// ClassPathXmlApplicationContext(
	// "/META-INF/spring/app-context.xml");
	//
	// pAnnotations.setParent(pXML);
	//
	// IFasadaWrapperDAO pBean1 = (IFasadaWrapperDAO)
	// pAnnotations.getBean(IFasadaWrapperDAO.class);
	// System.out.println(pBean1);
	// }

}
