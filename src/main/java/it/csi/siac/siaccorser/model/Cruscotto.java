/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * La classe Cruscotto contiene tutte le informazini necessarie
 * per la visualizzazione delle azioni pendenti, delle notifiche
 * e delle azioni che l'operatore può eseguire
 * 
 * @author Spin Servizi per l'Innovazione
 *
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class Cruscotto implements Serializable {

	private static final long serialVersionUID = 783947548461634479L;
	
	private List<GruppoAttivitaPendenti> gruppiAttivitaPendenti = new ArrayList<GruppoAttivitaPendenti>();
	private List<GruppoNotifichePendenti> gruppiNotifichePendenti = new ArrayList<GruppoNotifichePendenti>();
	private List<GruppoNotificaOperazioneAsincrona> gruppiNotificheOperazioneAsincrona = new ArrayList<GruppoNotificaOperazioneAsincrona>();
	private List<Azione> azioniFrequenti = new ArrayList<Azione>();
	private List<GruppoAzioni> gruppiAzioni = new ArrayList<GruppoAzioni>();
	private List<AzioneConsentita> azioniConsentite = new ArrayList<AzioneConsentita>();
	private String messaggioInformativo;
	
	private Map<Integer, Azione> lookupAzioneById;
	private Map<String, Azione> lookupAzioneByNome;


	private List<AnnoBilancio> anniBilancio = new ArrayList<AnnoBilancio>();
	private AnnoBilancio annoBilancio;

	public void initLookupAzioneCaches() {
		lookupAzioneById = new HashMap<Integer, Azione>();
		lookupAzioneByNome = new HashMap<String, Azione>();
		
		for (Azione azione: getAzioniFrequenti() ) {
			cacheAzione(azione);
		}
		
		for (GruppoAzioni gruppoAzioni:getGruppiAzioni() ) {
			for (Azione azione:gruppoAzioni.getAzioni() ) {
				cacheAzione(azione);
			}
		}
		
		for (AzioneConsentita ac: getAzioniConsentite() ) {
			cacheAzione(ac.getAzione());
		}
	}
	
	private void cacheAzione(Azione azione) {
		
		if (lookupAzioneById.containsKey(azione.getUid())) {
			return;
		}
		
		lookupAzioneById.put(azione.getUid(), azione);
		lookupAzioneByNome.put(azione.getNome(), azione);
	}
	
	public Azione findAzione(Integer id) {
		
		return lookupAzioneById.get(id);
	}

	public Azione findAzioneByNome(String nome) {
		
		return lookupAzioneByNome.get(nome);
	}
	
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


	/**
	 * @return the gruppiAzioni
	 */
	public List<GruppoAzioni> getGruppiAzioni() {
		return gruppiAzioni;
	}


	/**
	 * @param gruppiAzioni the gruppiAzioni to set
	 */
	public void setGruppiAzioni(List<GruppoAzioni> gruppiAzioni) {
		this.gruppiAzioni = gruppiAzioni;
	}


	/**
	 * @return the azioniConsentite
	 */
	public List<AzioneConsentita> getAzioniConsentite() {
		return azioniConsentite;
	}


	/**
	 * @param azioniConsentite the azioniConsentite to set
	 */
	public void setAzioniConsentite(List<AzioneConsentita> azioniConsentite) {
		this.azioniConsentite = azioniConsentite;
	}


	/**
	 * @return the anniBilancio
	 */
	public List<AnnoBilancio> getAnniBilancio() {
		return anniBilancio;
	}


	/**
	 * @param anniBilancio the anniBilancio to set
	 */
	public void setAnniBilancio(List<AnnoBilancio> anniBilancio) {
		this.anniBilancio = anniBilancio;
	}


	/**
	 * @return the annoBilancio
	 */
	public AnnoBilancio getAnnoBilancio() {
		return annoBilancio;
	}


	/**
	 * @param annoBilancio the annoBilancio to set
	 */
	public void setAnnoBilancio(AnnoBilancio annoBilancio) {
		this.annoBilancio = annoBilancio;
	}
	
	public List<GruppoNotificaOperazioneAsincrona> getGruppiNotificheOperazioneAsincrona() {
		return gruppiNotificheOperazioneAsincrona;
	}


	public void setGruppiNotificheOperazioneAsincrona(
			List<GruppoNotificaOperazioneAsincrona> gruppiNotificheOperazioneAsincrona) {
		this.gruppiNotificheOperazioneAsincrona = gruppiNotificheOperazioneAsincrona;
	}


	public String getMessaggioInformativo() {
		return messaggioInformativo;
	}


	public void setMessaggioInformativo(String messaggioInformativo) {
		this.messaggioInformativo = messaggioInformativo;
	}

	@XmlTransient
	public Map<Integer, Azione> getLookupAzioneById() {
		return lookupAzioneById;
	}

	public void setLookupAzioneById(Map<Integer, Azione> lookupAzioneById) {
		this.lookupAzioneById = lookupAzioneById;
	}

	@XmlTransient
	public Map<String, Azione> getLookupAzioneByNome() {
		return lookupAzioneByNome;
	}

	public void setLookupAzioneByNome(Map<String, Azione> lookupAzioneByNome) {
		this.lookupAzioneByNome = lookupAzioneByNome;
	}

}
