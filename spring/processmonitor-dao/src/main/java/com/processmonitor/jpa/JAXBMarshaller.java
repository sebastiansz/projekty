/***********************************************************
 * Autorskie Prawa Majatkowe -
 * Agencja Restrukturyzacji i Modernizacji Rolnictwa (ARiMR).
 *
 * Copyright 2012 The Agency for Restructuring and
 * Modernisation of Agriculture (ARMA).
 ************************************************************
 * Opis: patrz niżej w komentarzach javadoc.
 ************************************************************
 * Osoba odpowiedzialna: Sebastian Szabram
 ************************************************************/

package com.processmonitor.jpa;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

/**
 * Klasa obsługi JAXB.
 * 
 * @author sebastian
 */
public class JAXBMarshaller {

	/**
	 * Serializuje obiekt do XML
	 * 
	 * @param aXBVO
	 *            Obiekt do serializacji
	 * @param aNamespce
	 *            Namespace obiektu
	 * @return Obiekt po serializacji
	 */
	public static String marshallXBVO(Object aXBVO, String aNamespce) {
		StringWriter pXML = new StringWriter();
		try {
			QName pQName = new QName(aNamespce, aXBVO.getClass().getSimpleName());
			JAXBContext jaxbKontekst = JAXBContext.newInstance(aXBVO.getClass());
			Marshaller pMr = jaxbKontekst.createMarshaller();
			pMr.setProperty("jaxb.formatted.output", Boolean.TRUE);
			pMr.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
			pMr.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			// noinspection unchecked
			pMr.marshal(new JAXBElement(pQName, aXBVO.getClass(), aXBVO), pXML);
			return pXML.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Błąd serializacji JAXB dla obiektu: " + aXBVO + ", namespace: " + aNamespce, e);
		}
	}

	/**
	 * Serializuje obiekt do XML
	 * 
	 * @param aXBVO
	 *            Obiekt do serializacji
	 * @param aNamespce
	 *            Namespace obiektu
	 * @return Obiekt po serializacji
	 */
	public static String marshallXBVOXmlDecl(Object aXBVO, String aNamespce) {
		StringWriter pXML = new StringWriter();
		try {
			String pNazwaKlasy = aXBVO.getClass().getSimpleName();
			String pNazwaElementu = Character.toLowerCase(pNazwaKlasy.charAt(0)) + pNazwaKlasy.substring(1);
			QName pQName = new QName(aNamespce, pNazwaElementu);
			JAXBContext jaxbKontekst = JAXBContext.newInstance(aXBVO.getClass());
			Marshaller pMr = jaxbKontekst.createMarshaller();
			pMr.setProperty("jaxb.formatted.output", Boolean.TRUE);
			pMr.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.TRUE);
			pMr.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			pMr.marshal(new JAXBElement(pQName, aXBVO.getClass(), aXBVO), pXML);
			return pXML.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Błąd serializacji JAXB dla obiektu: " + aXBVO + ", namespace: " + aNamespce, e);
		}
	}

	/**
	 * Deserializuje XML
	 * 
	 * @param aKlasaXBVO
	 *            Klasa
	 * @param aXML
	 *            XML do deserializacji
	 * @return Obiekt po deserializacji
	 */
	public static <T> T unmarshallXBVO(Class<T> aKlasaXBVO, String aXML) {
		StringReader pTrescRD = new StringReader(aXML);
		try {
			JAXBContext jaxbKontekst = JAXBContext.newInstance(aKlasaXBVO);
			JAXBElement<T> pEL = jaxbKontekst.createUnmarshaller().unmarshal(new StreamSource(pTrescRD), aKlasaXBVO);
			return pEL.getValue();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Błąd deserializacji JAXB dla XML: " + aXML + ", klasa: " + aKlasaXBVO, e);
		}
	}

	/**
	 * Metoda deserializuje XML
	 * 
	 * @param aXML
	 *            XML
	 * @param aKlasaXBVO
	 *            Klasa obiektu
	 * @return Obiekt po deserializacji
	 * @throws JAXBException
	 *             Wyjątek
	 */
	public static <T> T unmarshallXBVO(InputStream aXML, Class<T> aKlasaXBVO) throws JAXBException {
		try {
			JAXBContext jaxbKontekst = JAXBContext.newInstance(aKlasaXBVO);
			JAXBElement<T> pEL = jaxbKontekst.createUnmarshaller().unmarshal(new StreamSource(aXML), aKlasaXBVO);
			return pEL.getValue();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Błąd deserializacji JAXB dla strumienia XML: " + aXML + ", klasa: "
					+ aKlasaXBVO, e);
		}
	}

	/**
	 * Metoda deserializuje XML
	 * 
	 * @param aXML
	 *            XML
	 * @param aKlasaXBVO
	 *            Klasa obiektu
	 * @param aPlikXSD
	 *            Położenie pliku XSD w pa_narzedzia.jar
	 * @param aEventHandler
	 *            Handler
	 * @return Obiekt po deserializacji
	 * @throws JAXBException
	 *             Wyjątek
	 */
	public static <T> T unmarshallXBVOValidate(InputStream aXML, Class<T> aKlasaXBVO, String aPlikXSD,
			ValidationEventCollector aEventHandler) throws JAXBException {

		SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema pSchema;
		try {
			pSchema = sf.newSchema(JAXBMarshaller.class.getResource(aPlikXSD));
		} catch (SAXException e) {
			e.printStackTrace();
			throw new RuntimeException("Błąd odczytu XSD", e);
		}
		JAXBContext jaxbKontekst = JAXBContext.newInstance(aKlasaXBVO);
		Unmarshaller pUnmarshaller = jaxbKontekst.createUnmarshaller();
		if (pUnmarshaller != null) {
			pUnmarshaller.setEventHandler(aEventHandler);
		}
		pUnmarshaller.setSchema(pSchema);
		JAXBElement<T> pEL = pUnmarshaller.unmarshal(new StreamSource(aXML), aKlasaXBVO);
		return pEL.getValue();
	}

}