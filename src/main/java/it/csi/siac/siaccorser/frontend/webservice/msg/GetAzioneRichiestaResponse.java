/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.ServiceResponse;

/**
 * Messaggio di risposta alla richiesta delle informazioni associate ad una
 * azione richiesta
 * 
 * @author Spin Servizi per l'Innovazione
 *
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetAzioneRichiestaResponse extends ServiceResponse {
	
	private AzioneRichiesta azioneRichiesta;
	private List<AzioneConsentita> azioniConsentite;
	/**
	 * @return the azioneRichiesta
	 */
	public AzioneRichiesta getAzioneRichiesta() {
		return azioneRichiesta;
	}
	/**
	 * @param azioneRichiesta the azioneRichiesta to set
	 */
	public void setAzioneRichiesta(AzioneRichiesta azioneRichiesta) {
		this.azioneRichiesta = azioneRichiesta;
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

}
