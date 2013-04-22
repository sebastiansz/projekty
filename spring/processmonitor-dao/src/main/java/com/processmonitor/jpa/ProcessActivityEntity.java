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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Klasa reprezentująca krok wykonania procesu.
 * 
 * @author sebastian
 */
@Entity
@SequenceGenerator(name = "PA_SEQ", sequenceName = "PA_SEQ", allocationSize = 1)
@Table(name = "KROKI_PROCESOW")
@NamedQueries(value = {
		@NamedQuery(name = "ProcessActivityEntity.szukajPoNazwie", query = "from ProcessActivityEntity ob where ob.procesIntegracyjny.id = :p_idProcesu and ob.nazwa = :p_nazwa"),
		@NamedQuery(name = "ProcessActivityEntity.pobierzStanPoNazwie", query = "select ob.stan from ProcessActivityEntity ob where ob.procesIntegracyjny.id = :p_idProcesu and ob.nazwa = :p_nazwa") })
public class ProcessActivityEntity extends AbstractJPA {

	private static final long serialVersionUID = 1L;

	/**
	 * Proces
	 */
	@ManyToOne
	@JoinColumn(name = "PROCES_FK", nullable = false)
	protected ProcessEntity procesIntegracyjny;

	/**
	 * Log zdarzeń wykonania
	 */
	@OneToMany(mappedBy = "krok", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ActivityErrorEntity> blad = new ArrayList<ActivityErrorEntity>();

	/**
	 * Zmienna request
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ZM_REQUEST_FK", nullable = true)
	protected ProcessVariableEntity zmiennaRequest;

	/**
	 * Zmienna response
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ZM_RESPONSE_FK", nullable = true)
	protected ProcessVariableEntity zmiennaResponse;

	/**
	 * Nazwa procesu integracyjnego
	 */
	@Column(name = "NAZWA", nullable = false, length = 1024)
	protected String nazwa;

	/**
	 * Nazwa procesu integracyjnego
	 */
	@Column(name = "KOMUNIKAT", nullable = true, length = 1024)
	protected String komunikat;

	/**
	 * Stan
	 */
	@Column(name = "STAN", nullable = false)
	protected Long stan;

	/**
	 * Maks. liczba powtórzeń
	 */
	@Column(name = "MAKS_LICZBA_POWTORZEN")
	protected Integer maksLiczbaPowtorzen;

	/**
	 * Liczba wywołań
	 */
	@Column(name = "LICZBA_WYWOLAN")
	protected Integer liczbaWywolan;

	/**
	 * Opóźnienie ponowienia
	 */
	@Column(name = "OPOZNIENIE_PONOWIENIA")
	protected Long opoznieniePonowienia;

	/** Data modyfikacji */
	@Column(name = "T_DATA_MODYFIKACJI", insertable = false, updatable = false, columnDefinition = "TIMESTAMP(6)")
	protected Date dataModyfikacji;

	/**
	 * Konstruktor
	 */
	public ProcessActivityEntity() {
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
	 * @param aProcesIntegracyjny
	 *            Parametr aktualizuje wartość atrybutu <code>procesIntegracyjny</code>.
	 */
	public void setProcesIntegracyjny(ProcessEntity aProcesIntegracyjny) {
		procesIntegracyjny = aProcesIntegracyjny;
	}

	/**
	 * Zwraca wartość atrybutu <code>etapWykonaniaProcesu</code>.
	 * 
	 * @return Wartośc atrybutu <code>etapWykonaniaProcesu</code>.
	 */
	public String getNazwa() {
		return nazwa;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>etapWykonaniaProcesu</code>.
	 * 
	 * @param aEtapWykonaniaProcesu
	 *            Parametr aktualizuje wartość atrybutu <code>etapWykonaniaProcesu</code>.
	 */
	public void setNazwa(String aEtapWykonaniaProcesu) {
		nazwa = aEtapWykonaniaProcesu;
	}

	/**
	 * Zwraca wartość atrybutu <code>komunikat</code>.
	 * 
	 * @return Wartośc atrybutu <code>komunikat</code>.
	 */
	public String getKomunikat() {
		return komunikat;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>komunikat</code>.
	 * 
	 * @param aKomunikat
	 *            Parametr aktualizuje wartość atrybutu <code>komunikat</code>.
	 */
	public void setKomunikat(String aKomunikat) {
		komunikat = aKomunikat;
	}

	/**
	 * Zwraca wartość atrybutu <code>stan</code>.
	 * 
	 * @return Wartośc atrybutu <code>stan</code>.
	 */
	public Long getStan() {
		return stan;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>stan</code>.
	 * 
	 * @param aStan
	 *            Parametr aktualizuje wartość atrybutu <code>stan</code>.
	 */
	public void setStan(Long aStan) {
		stan = aStan;
	}

	/**
	 * Zwraca wartość atrybutu <code>maksLiczbaPowtorzen</code>.
	 * 
	 * @return Wartośc atrybutu <code>maksLiczbaPowtorzen</code>.
	 */
	public Integer getMaksLiczbaPowtorzen() {
		return maksLiczbaPowtorzen;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>maksLiczbaPowtorzen</code>.
	 * 
	 * @param aMaksLiczbaPowtorzen
	 *            Parametr aktualizuje wartość atrybutu <code>maksLiczbaPowtorzen</code>.
	 */
	public void setMaksLiczbaPowtorzen(Integer aMaksLiczbaPowtorzen) {
		maksLiczbaPowtorzen = aMaksLiczbaPowtorzen;
	}

	/**
	 * Zwraca wartość atrybutu <code>liczbaWywolan</code>.
	 * 
	 * @return Wartośc atrybutu <code>liczbaWywolan</code>.
	 */
	public Integer getLiczbaWywolan() {
		return liczbaWywolan;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>liczbaWywolan</code>.
	 * 
	 * @param aLiczbaWywolan
	 *            Parametr aktualizuje wartość atrybutu <code>liczbaWywolan</code>.
	 */
	public void setLiczbaWywolan(Integer aLiczbaWywolan) {
		liczbaWywolan = aLiczbaWywolan;
	}

	/**
	 * Zwraca wartość atrybutu <code>opoznieniePonowienia</code>.
	 * 
	 * @return Wartośc atrybutu <code>opoznieniePonowienia</code>.
	 */
	public Long getOpoznieniePonowienia() {
		return opoznieniePonowienia;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>opoznieniePonowienia</code>.
	 * 
	 * @param aOpoznieniePonowienia
	 *            Parametr aktualizuje wartość atrybutu <code>opoznieniePonowienia</code>.
	 */
	public void setOpoznieniePonowienia(Long aOpoznieniePonowienia) {
		opoznieniePonowienia = aOpoznieniePonowienia;
	}

	/**
	 * Zwraca wartość atrybutu <code>blad</code>.
	 * 
	 * @return Wartośc atrybutu <code>blad</code>.
	 */
	public List<ActivityErrorEntity> getBlad() {
		return blad;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>blad</code>.
	 * 
	 * @param aBlad
	 *            Parametr aktualizuje wartość atrybutu <code>blad</code>.
	 */
	public void setBlad(List<ActivityErrorEntity> aBlad) {
		blad = aBlad;
	}

	/**
	 * Dodaje błąd.
	 * 
	 * @param aBlad
	 *            Błąd kroku
	 */
	public void dodajBlad(ActivityErrorEntity aBlad) {
		getBlad().add(aBlad);
		aBlad.setKrok(this);
	}

	/**
	 * Zwraca wartość atrybutu <code>zmiennaRequest</code>.
	 * 
	 * @return Wartośc atrybutu <code>zmiennaRequest</code>.
	 */
	public ProcessVariableEntity getZmiennaRequest() {
		return zmiennaRequest;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>zmiennaRequest</code>.
	 * 
	 * @param aZmiennaRequest
	 *            Parametr aktualizuje wartość atrybutu <code>zmiennaRequest</code>.
	 */
	public void setZmiennaRequest(ProcessVariableEntity aZmiennaRequest) {
		zmiennaRequest = aZmiennaRequest;
	}

	/**
	 * Zwraca wartość atrybutu <code>zmiennaResponse</code>.
	 * 
	 * @return Wartośc atrybutu <code>zmiennaResponse</code>.
	 */
	public ProcessVariableEntity getZmiennaResponse() {
		return zmiennaResponse;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>zmiennaResponse</code>.
	 * 
	 * @param aZmiennaResponse
	 *            Parametr aktualizuje wartość atrybutu <code>zmiennaResponse</code>.
	 */
	public void setZmiennaResponse(ProcessVariableEntity aZmiennaResponse) {
		zmiennaResponse = aZmiennaResponse;
	}

	/**
	 * Zwraca wartość atrybutu <code>dataModyfikacji</code>.
	 * 
	 * @return Wartośc atrybutu <code>dataModyfikacji</code>.
	 */
	public Date getDataModyfikacji() {
		return dataModyfikacji;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>dataModyfikacji</code>.
	 * 
	 * @param aDataModyfikacji
	 *            Parametr aktualizuje wartość atrybutu <code>dataModyfikacji</code>.
	 */
	public void setDataModyfikacji(Date aDataModyfikacji) {
		dataModyfikacji = aDataModyfikacji;
	}
}
