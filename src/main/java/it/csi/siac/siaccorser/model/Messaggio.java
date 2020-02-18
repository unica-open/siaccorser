/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Codifica dei messaggi
 * 
 * @author 
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Messaggio implements Serializable{

	private static final long serialVersionUID = 159049302155438669L;
	
	private String codice;
	private String descrizione;

	public Messaggio (){
		super();
	}
	
	public Messaggio(String codice, String descrizione) {
		this();
		this.codice = codice;
		this.descrizione = descrizione;
	}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice
	 *            the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione
	 *            the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
