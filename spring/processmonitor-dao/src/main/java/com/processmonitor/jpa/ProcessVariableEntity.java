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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Klasa reprezentująca zmienną procesu.
 */
@Entity
@SequenceGenerator(name = "PA_SEQ", sequenceName = "PA_SEQ", allocationSize = 1)
@Table(name = "ZMIENNE_PROCESOW")
@NamedQueries(value = {
        @NamedQuery(name = "ProcessVariableEntity.szukajPoNazwie", query = "from ProcessVariableEntity ob where ob.procesIntegracyjny.id=:p_idProcesu and ob.nazwa = :p_nazwa"),
        @NamedQuery(name = "ProcessVariableEntity.szukajPoIdProcesu", query = "from ProcessVariableEntity ob where ob.procesIntegracyjny.id=:p_idProcesu") })
public class ProcessVariableEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * Proces
	 */
	@ManyToOne
	@JoinColumn(name = "PROCES_FK", nullable = false)
	protected ProcessEntity procesIntegracyjny;

	/**
	 * Nazwa procesu integracyjnego
	 */
	@Column(name = "NAZWA", nullable = false, length = 1024)
	protected String nazwa;

	/**
	 * Nazwa procesu integracyjnego
	 */
	@Column(name = "NAZWA_KLASY", nullable = false, length = 1024)
	protected String nazwaKlasy;

	/**
	 * Nazwa procesu integracyjnego
	 */
	@Column(name = "NAMESPACE", nullable = false, length = 1024)
	protected String namespace;

	/**
	 * Payload
	 */
	@Lob
	@Column(name = "PAYLOAD")
	@Basic(fetch = FetchType.LAZY)
	protected String payload;

	/**
	 * Konstruktor
	 */
	public ProcessVariableEntity() {
	}

	/**
	 * Metoda deserializuje payload.
	 * 
	 * @param <T> Typ XBVO
	 * 
	 * @return XBVO
	 */
	@SuppressWarnings("unchecked")
	@Transient
	public <T> T getPayloadJAXB() {
		return (T) JAXBMarshaller.unmarshallXBVO(getKlasaXBVO(), payload);
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
		if (aXBVO != null) {
			setPayload(JAXBMarshaller.marshallXBVO(aXBVO, aNamespace));
			setNazwaKlasy(aXBVO.getClass().getName());
			setNamespace(aNamespace);
		} else {
			setNazwaKlasy("brak");
			setNamespace("brak");
			setPayload(null);
		}
		return payload;
	}

	/**
	 * Zwraca wartość atrybutu <code>procesIntegracyjny</code>.
	 * 
	 * @return Wartośc atrybutu <code>procesIntegracyjny</code>.
	 */
	public ProcessEntity getProcesIntegracyjny() {
		return procesIntegracyjny;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>procesIntegracyjny</code>.
	 * 
	 * @param aProcesIntegracyjny Parametr aktualizuje wartość atrybutu <code>procesIntegracyjny</code>.
	 */
	public void setProcesIntegracyjny(ProcessEntity aProcesIntegracyjny) {
		procesIntegracyjny = aProcesIntegracyjny;
	}

	/**
	 * Zwraca wartość atrybutu <code>nazwa</code>.
	 * 
	 * @return Wartośc atrybutu <code>nazwa</code>.
	 */
	public String getNazwa() {
		return nazwa;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>nazwa</code>.
	 * 
	 * @param aNazwa Parametr aktualizuje wartość atrybutu <code>nazwa</code>.
	 */
	public void setNazwa(String aNazwa) {
		nazwa = aNazwa;
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
	 * Zwraca wartość atrybutu <code>klasaWyniku</code>.
	 * 
	 * @return Wartośc atrybutu <code>klasaWyniku</code>.
	 */
	public String getNazwaKlasy() {
		return nazwaKlasy;
	}

	/**
	 * Zwraca klasę wyniku.
	 * 
	 * @return Klasa
	 */
	@Transient
	public Class<?> getKlasaXBVO() {
		try {
			return Class.forName(nazwaKlasy);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Nie prawidłowa nazwa klasy XBVO w zmiennej procesu: " + nazwaKlasy, e);
		}
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>klasaWyniku</code>.
	 * 
	 * @param aKlasaWyniku Parametr aktualizuje wartość atrybutu <code>klasaWyniku</code>.
	 */
	private void setNazwaKlasy(String aKlasaWyniku) {
		nazwaKlasy = aKlasaWyniku;
	}

	/**
	 * Zwraca wartość atrybutu <code>namespace</code>.
	 * 
	 * @return Wartośc atrybutu <code>namespace</code>.
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>namespace</code>.
	 * 
	 * @param aNamespace Parametr aktualizuje wartość atrybutu <code>namespace</code>.
	 */
	private void setNamespace(String aNamespace) {
		namespace = aNamespace;
	}

}
