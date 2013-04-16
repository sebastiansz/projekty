package com.processmonitor.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Bazowe Entity z wersją.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PA_SEQ")
	@SequenceGenerator(name = "PA_SEQ", sequenceName = "PA_SEQ", allocationSize = 1)
	protected Long id;

	@Version
	@Column(name = "WERSJA")
	protected Long wersja;

	/**
	 * Tworzy nową instancję bazowe entity.
	 */
	public AbstractEntity() {
	}

	/**
	 * Tworzy nową instancję bazowe entity.
	 * 
	 * @param id
	 *            id
	 */
	public AbstractEntity(Long id) {
		this.id = id;
	}

	/**
	 * Zwraca wersja (wartość pola wersja).
	 * 
	 * @return wersja
	 */
	public Long getWersja() {
		return wersja;
	}

	/**
	 * Ustawia wersja (wartość pola wersja).
	 * 
	 * @param aWersja
	 *            wersja
	 */
	public void setWersja(Long aWersja) {
		wersja = aWersja;
	}

	/**
	 * Zwraca id (wartość pola id).
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Ustawia id (wartość pola id).
	 * 
	 * @param aId
	 *            id
	 */
	public void setId(Long aId) {
		id = aId;
	}

}
