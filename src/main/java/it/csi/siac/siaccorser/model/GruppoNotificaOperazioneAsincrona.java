/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.paginazione.ListaPaginata;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginataImpl;

/**
 * Descrittore del gruppo di notifiche di operazione asincrona che raccoglie le
 * azioni secondo aree funzionali
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class GruppoNotificaOperazioneAsincrona implements Serializable {

	private static final long serialVersionUID = -8057212098513063049L;

	private Azione azione;
	private int totali;
	private int urgenti;
	private ListaPaginata<NotificaOperazioneAsincrona> notificheOperazioneAsincrona = new ListaPaginataImpl<NotificaOperazioneAsincrona>();

	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}

	public int getTotali() {
		return totali;
	}

	public void setTotali(int totali) {
		this.totali = totali;
	}

	public int getUrgenti() {
		return urgenti;
	}

	public void setUrgenti(int urgenti) {
		this.urgenti = urgenti;
	}

	public ListaPaginata<NotificaOperazioneAsincrona> getNotificheOperazioneAsincrona() {
		return notificheOperazioneAsincrona;
	}

	public void setNotificheOperazioneAsincrona(
			ListaPaginata<NotificaOperazioneAsincrona> notificheOperazioneAsincrona) {
		this.notificheOperazioneAsincrona = notificheOperazioneAsincrona;
	}

	

}
