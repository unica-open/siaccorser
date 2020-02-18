/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;


/**
 * Classe per la gestione delle informazioni lato GUI.
 * 
 * @author Alessandro Marchino
 * @version 1.0.0
 *
 */
public class Informazione extends Codifica {
	
	/** Per la serializzazione  */
	private static final long serialVersionUID = -1179739981682084640L;

	/** Costruttore vuoto di default */
	public Informazione() {
		super();
	}
	
	/**
	 * Costruttore dati codice e descrizione.
	 * 
	 * @param codice		il codice da impostare
	 * @param descrizione	la descrizione da impostare
	 */
	public Informazione(String codice, String descrizione) {
		super(codice, descrizione);
	}
	
	/**
	 * Restituisce una String con il testo dell'informazione.
	 * 
	 * @return il testo dell'Informazione
	 */
	public String getTesto() {
		return String.format("%s - %s", getCodice(), getDescrizione());
	}
	
}
