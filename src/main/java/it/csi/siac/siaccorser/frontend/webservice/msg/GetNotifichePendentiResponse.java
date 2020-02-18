/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.NotificaPendente;
import it.csi.siac.siaccorser.model.ServiceResponse;

/**
 * Messaggio di risposta alla richiesta della lista delle
 * notifiche pendenti di un account
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetNotifichePendentiResponse extends ServiceResponse {

	private List<NotificaPendente> notifichePendenti;

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
