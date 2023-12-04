/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.NotificaPendente;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di risposta alla richiesta di una lista di notifiche 
 * pendenti (task del bpm)
 * 
 * @author Spin Servizi per l'Innovazione
 *
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class RicercaNotifichePendentiResponse extends ServiceRequest {
	
	int totali;
	int urgenti;
	int offset;
	int size;
	List<NotificaPendente> notifichePendenti;
	/**
	 * @return the totali
	 */
	public int getTotali() {
		return totali;
	}
	/**
	 * @param totali the totali to set
	 */
	public void setTotali(int totali) {
		this.totali = totali;
	}
	/**
	 * @return the urgenti
	 */
	public int getUrgenti() {
		return urgenti;
	}
	/**
	 * @param urgenti the urgenti to set
	 */
	public void setUrgenti(int urgenti) {
		this.urgenti = urgenti;
	}
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the notifichePendenti
	 */
	public List<NotificaPendente> getNotifichePendenti() {
		return notifichePendenti;
	}
	/**
	 * @param notifichePendenti the notifichePendenti to set
	 */
	public void setNotifichePendenti(List<NotificaPendente> notifichePendenti) {
		this.notifichePendenti = notifichePendenti;
	}
}
