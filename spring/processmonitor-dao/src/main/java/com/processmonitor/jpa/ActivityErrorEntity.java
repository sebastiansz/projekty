/************************************************************
 * Autorsk ie Prawa Majatkowe -
 * Agencja Restrukturyzacji i Modernizacji Rolnictwa (ARiMR).
 *
 * Copyright 2012 The Agency for Restructuring and
 * Modernisation of Agriculture (ARMA).
 ************************************************************
 * Opis: patrz nizej w komentarzach javadoc.
 ************************************************************
 * Osoba odpowiedzialna: Sebastian Szabram
 * Data utworzenia: 2012-03-28
 ************************************************************/
package com.processmonitor.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Klasa reprezentująca zmienną procesu.
 * 
 * @author sebastian
 */
@Entity
@SequenceGenerator(name = "PA_SEQ", sequenceName = "PA_SEQ", allocationSize = 1)
@Table(name = "BLEDY_KROKOW")
public class ActivityErrorEntity extends AbstractJPA {

	private static final long serialVersionUID = 1L;

	/**
	 * Proces
	 */
	@ManyToOne
	@JoinColumn(name = "KROK_FK", nullable = false)
	protected ProcessActivityEntity krok;

	/**
	 * Payload
	 */
	@Lob
	@Column(name = "PAYLOAD")
	protected String payload;

	/**
	 * Konstruktor
	 */
	public ActivityErrorEntity() {
	}

	/**
	 * Metoda deserializuje payload.
	 * 
	 * @param <T> Typ XBVO
	 * 
	 * @param aKlasaXBVO Klasa
	 * @return XBVO
	 */
	@Transient
	public <T> T getPayloadJAXB(Class<T> aKlasaXBVO) {
		return JAXBMarshaller.unmarshallXBVO(aKlasaXBVO, payload);
	}

	/**
	 * Serializuje XBVO z XML
	 * 
	 * @param aXBVO Obiekt do serialiacji
	 * @param aNamespace Namespace
	 * @return XML po serializacji obiektu
	 */
	@Transient
	public String setPayloadJAXB(Object aXBVO, String aNamespace) {
		payload = JAXBMarshaller.marshallXBVO(aXBVO, aNamespace);
		return payload;
	}

	/**
	 * Zwraca wartość atrybutu <code>payload</code>.
	 * 
	 * @return Wartośc atrybutu <code>payload</code>.
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>payload</code>.
	 * 
	 * @param aPayload Parametr aktualizuje wartość atrybutu <code>payload</code>.
	 */
	public void setPayload(String aPayload) {
		payload = aPayload;
	}

	/**
	 * Zwraca wartość atrybutu <code>krok</code>.
	 * 
	 * @return Wartośc atrybutu <code>krok</code>.
	 */
	public ProcessActivityEntity getKrok() {
		return krok;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>krok</code>.
	 * 
	 * @param aKrok Parametr aktualizuje wartość atrybutu <code>krok</code>.
	 */
	public void setKrok(ProcessActivityEntity aKrok) {
		krok = aKrok;
	}

}
