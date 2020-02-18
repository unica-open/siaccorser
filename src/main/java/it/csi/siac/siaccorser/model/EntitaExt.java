/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Estende Entita con informazioni legate al login di modifica, 
 * creazione, cancellazione ed alla data modifica e cancellazione.
 * 
 * Per ragione di compatibilit&agrave; con il pregresso non &eacute; stato accorpato direttamente ad Entita
 * ma in un successivo step si può valutare tale possibilit&agrave;.
 *
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class EntitaExt extends Entita {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3395435527405505347L;
	
	
	private String loginCreazione;//new
	private String loginModifica;//new
	private String loginCancellazione;//new
	
	
	
	public String getLoginCreazione() {
		return loginCreazione;
	}

	public void setLoginCreazione(String loginCreazione) {
		this.loginCreazione = loginCreazione;
	}

	public String getLoginModifica() {
		return loginModifica;
	}

	public void setLoginModifica(String loginModifica) {
		this.loginModifica = loginModifica;
	}

	public String getLoginCancellazione() {
		return loginCancellazione;
	}

	public void setLoginCancellazione(String loginCancellazione) {
		this.loginCancellazione = loginCancellazione;
	}

}
