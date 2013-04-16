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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Encja reprezentuje proces integracyjny.
 * 
 * @author sebastian
 */
@Entity
@SequenceGenerator(name = "PA_SEQ", sequenceName = "PA_SEQ", allocationSize = 1)
@Table(name = "PROCESY_INTEGRACYJNE")
@NamedQueries(value = {
        @NamedQuery(name = "ProcesIntegracyjnyEntity.szukajPoNazwie", query = "from ProcesIntegracyjnyEntity ob where ob.nazwaInstancji = :p_nazwaInstancji"),
        @NamedQuery(name = "ProcesIntegracyjnyEntity.szukajPoStanie", query = "from ProcesIntegracyjnyEntity ob where ob.stan = :p_stan"),
        @NamedQuery(name = "ProcesIntegracyjnyEntity.pobierzLogin", query = "select ob.login from ProcesIntegracyjnyEntity ob where ob.id = :p_idProcesu") })
public class ProcessEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * ID procesu integracyjnego
	 */
	@Column(name = "NAZWA_INSTANCJI", nullable = true, unique = true, length = 1024)
	private String nazwaInstancji;

	/**
	 * Stan procesu integracyjnego (ENUM?)
	 */
	@Column(name = "STAN", nullable = false)
	private Long stan;

	/**
	 * Login użytkownika, który uruchomił proces
	 */
	@Column(name = "LOGIN", nullable = false)
	private String login;

	/**
	 * Zmienne proceu
	 */
	@OneToMany(mappedBy = "procesIntegracyjny", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProcessVariableEntity> zmienneProcesu = new ArrayList<ProcessVariableEntity>();

	/**
	 * Log zdarzeń wykonania
	 */
	@OneToMany(mappedBy = "procesIntegracyjny", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("dataModyfikacji ASC")
	private List<ProcessActivityEntity> krokProcesu = new ArrayList<ProcessActivityEntity>();

	/**
	 * Konstruktor
	 */
	public ProcessEntity() {
	}

	/**
	 * Dodaje zmienną procesu.
	 * 
	 * @param aZmienna
	 *            Zmienna procesu
	 */
	public void dodajZmienneProcesu(ProcessVariableEntity aZmienna) {
		getZmienneProcesu().add(aZmienna);
		aZmienna.setProcesIntegracyjny(this);
	}

	/**
	 * 
	 * Metoda zwraca zmienną procesu wg nazwy
	 * 
	 * @param aNazwaZmiennej
	 *            Nazwa zmiennej procesu
	 * @return Zmienna procesu
	 */
	public ProcessVariableEntity pobierzZmienneProcesu(String aNazwaZmiennej) {
		for (ProcessVariableEntity pZmienna : getZmienneProcesu()) {
			if (aNazwaZmiennej.equals(pZmienna.getNazwa())) {
				return pZmienna;
			}
		}
		return null;
	}

	/**
	 * Zwraca encję proku
	 * 
	 * @param aNazwaKroku
	 *            Nazwa
	 * @return Encja
	 */
	public ProcessActivityEntity pobierzKrokProcesu(String aNazwaKroku) {
		for (ProcessActivityEntity pKrok : getKrokProcesu()) {
			if (aNazwaKroku.equals(pKrok.getNazwa())) {
				return pKrok;
			}
		}
		return null;
	}

	/**
	 * Dodaje krok procesu.
	 * 
	 * @param aZdarzenie
	 *            Encja zdarzenia
	 */
	public void dodajKrok(ProcessActivityEntity aZdarzenie) {
		getKrokProcesu().add(aZdarzenie);
		aZdarzenie.setProcesIntegracyjny(this);
	}

	/**
	 * Usuwa krok procesu.
	 * 
	 * @param aKrok
	 *            Encja zdarzenia
	 */
	public void usunKrok(ProcessActivityEntity aKrok) {
		getKrokProcesu().remove(aKrok);
		aKrok.setProcesIntegracyjny(null);
	}

	/**
	 * Zwraca ostatnio dodane zdarzenie
	 * 
	 * @return Zdarzenie
	 */
	@Transient
	public ProcessActivityEntity getOstatnieZdarzenie() {
		Integer pRozmiar = getKrokProcesu().size();
		if (pRozmiar > 0) {
			return getKrokProcesu().get(pRozmiar - 1);
		}
		return null;
	}

	/**
	 * Zwraca wartość atrybutu <code>nazwaInstancji</code>.
	 * 
	 * @return Wartośc atrybutu <code>nazwaInstancji</code>.
	 */
	public String getNazwaInstancji() {
		return nazwaInstancji;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>nazwaInstancji</code>.
	 * 
	 * @param aNazwaInstancji
	 *            Parametr aktualizuje wartość atrybutu
	 *            <code>nazwaInstancji</code>.
	 */
	public void setNazwaInstancji(String aNazwaInstancji) {
		nazwaInstancji = aNazwaInstancji;
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
	 * Zwraca wartość atrybutu <code>zmienneProcesu</code>.
	 * 
	 * @return Wartośc atrybutu <code>zmienneProcesu</code>.
	 */
	public List<ProcessVariableEntity> getZmienneProcesu() {
		return zmienneProcesu;
	}

	/**
	 * Usuwa krok procesu.
	 * 
	 * @param aZmienna
	 *            Zmienna
	 * 
	 */
	public void usunZmienna(ProcessVariableEntity aZmienna) {
		getZmienneProcesu().remove(aZmienna);
		aZmienna.setProcesIntegracyjny(null);
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>zmienneProcesu</code>.
	 * 
	 * @param aZmienneProcesu
	 *            Parametr aktualizuje wartość atrybutu
	 *            <code>zmienneProcesu</code>.
	 */
	public void setZmienneProcesu(List<ProcessVariableEntity> aZmienneProcesu) {
		zmienneProcesu = aZmienneProcesu;
	}

	/**
	 * Zwraca wartość atrybutu <code>krokProcesu</code>.
	 * 
	 * @return Wartośc atrybutu <code>krokProcesu</code>.
	 */
	public List<ProcessActivityEntity> getKrokProcesu() {
		return krokProcesu;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>krokProcesu</code>.
	 * 
	 * @param aKrokProcesu
	 *            Parametr aktualizuje wartość atrybutu <code>krokProcesu</code>
	 *            .
	 */
	protected void setKrokProcesu(List<ProcessActivityEntity> aKrokProcesu) {
		krokProcesu = aKrokProcesu;
	}

	/**
	 * Zwraca wartość atrybutu <code>login</code>.
	 * 
	 * @return Wartośc atrybutu <code>login</code>.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Ustawia nową wartość dla atrybutu <code>login</code>.
	 * 
	 * @param aLogin
	 *            Parametr aktualizuje wartość atrybutu <code>login</code>.
	 */
	public void setLogin(String aLogin) {
		login = aLogin;
	}

}
