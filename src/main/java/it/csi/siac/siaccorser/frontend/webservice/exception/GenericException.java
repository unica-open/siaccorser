/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.exception;

import java.util.ArrayList;
import java.util.List;

import it.csi.siac.siaccorser.model.Errore;

/**
 * Classe astratta per la gestione delle eccezioni
 * 
 * @author Spin Servizi per l'innovazione
 * 
 */
public abstract class GenericException extends RuntimeException {

	private static final long serialVersionUID = -6887572962078816981L;
	private final List<Errore> errori;

	/**
	 * Costruttore per singolo errore
	 * 
	 * @param errore
	 */
	public GenericException(Errore errore) {
		this();
		errori.add(errore);
	}

	/**
	 * Costruttore per lista di errori
	 * 
	 * @param errori
	 */
	public GenericException(List<Errore> errori) {
		this.errori = errori;
	}

	protected GenericException() {
		this.errori = new ArrayList<Errore>();
	}

	/**
	 * @return the errori
	 */
	public List<Errore> getErrori() {
		return errori;
	}

	public GenericException(String message) {
		super(message);
		this.errori = new ArrayList<Errore>();
	}
	
	
}
