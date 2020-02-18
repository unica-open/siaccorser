/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.GruppoAttivitaPendenti;
import it.csi.siac.siaccorser.model.GruppoNotificaOperazioneAsincrona;
import it.csi.siac.siaccorser.model.GruppoNotifichePendenti;
import it.csi.siac.siaccorser.model.ServiceResponse;

/**
 * Messaggio di risposta alla richiesta di aggiornamento delle 
 * informazioni visualizzate nel cruscotto di un operatore
 * 
 * @author Spin Servizi per l'Innovazione
 *
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class UpdateCruscottoResponse extends ServiceResponse {
	
	List<GruppoAttivitaPendenti> gruppiAttivitaPendenti = new ArrayList<GruppoAttivitaPendenti>();
	List<GruppoNotifichePendenti> gruppiNotifichePendenti = new ArrayList<GruppoNotifichePendenti>();
	private List<GruppoNotificaOperazioneAsincrona> gruppiNotificheOperazioneAsincrona = new ArrayList<GruppoNotificaOperazioneAsincrona>();
	List<Azione> azioniFrequenti = new ArrayList<Azione>();
	/**
	 * @return the gruppiAttivitaPendenti
	 */
	public List<GruppoAttivitaPendenti> getGruppiAttivitaPendenti() {
		return gruppiAttivitaPendenti;
	}
	/**
	 * @param gruppiAttivitaPendenti the gruppiAttivitaPendenti to set
	 */
	public void setGruppiAttivitaPendenti(
			List<GruppoAttivitaPendenti> gruppiAttivitaPendenti) {
		this.gruppiAttivitaPendenti = gruppiAttivitaPendenti;
	}
	/**
	 * @return the gruppiNotifichePendenti
	 */
	public List<GruppoNotifichePendenti> getGruppiNotifichePendenti() {
		return gruppiNotifichePendenti;
	}
	/**
	 * @param gruppiNotifichePendenti the gruppiNotifichePendenti to set
	 */
	public void setGruppiNotifichePendenti(
			List<GruppoNotifichePendenti> gruppiNotifichePendenti) {
		this.gruppiNotifichePendenti = gruppiNotifichePendenti;
	}
	/**
	 * @return the azioniFrequenti
	 */
	public List<Azione> getAzioniFrequenti() {
		return azioniFrequenti;
	}
	/**
	 * @param azioniFrequenti the azioniFrequenti to set
	 */
	public void setAzioniFrequenti(List<Azione> azioniFrequenti) {
		this.azioniFrequenti = azioniFrequenti;
	}
	public List<GruppoNotificaOperazioneAsincrona> getGruppiNotificheOperazioneAsincrona() {
		return gruppiNotificheOperazioneAsincrona;
	}
	public void setGruppiNotificheOperazioneAsincrona(
			List<GruppoNotificaOperazioneAsincrona> gruppiNotificheOperazioneAsincrona) {
		this.gruppiNotificheOperazioneAsincrona = gruppiNotificheOperazioneAsincrona;
	}
	
	
}
