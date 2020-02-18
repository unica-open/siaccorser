/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlType;

/**
 * Richiedente di un servizio. E' in qualche modo collegato all'utente.
 * 
 * @author alagna
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Richiedente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3092074844603608025L;
	private Operatore operatore;
	private String tokenOperazione;

	/**
	 * Id che serve a tener traccia della sessione applicativa dell'utente.
	 * Usato per fini di logging/auditing
	 */
	private String idSessione;
	private Account account;

	public Operatore getOperatore() {
		return operatore;
	}

	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}

	public String getTokenOperazione() {
		return tokenOperazione;
	}

	public void setTokenOperazione(String tokenOperazione) {
		this.tokenOperazione = tokenOperazione;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(String idSessione) {
		this.idSessione = idSessione;
	}

	/**
	 * Genera un nuovo id di sessione
	 * 
	 * @return
	 */
	public static String generaIdSessione() {
		return UUID.randomUUID().toString();
	}
}
