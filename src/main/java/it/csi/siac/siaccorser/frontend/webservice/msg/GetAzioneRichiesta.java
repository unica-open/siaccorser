/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta delle informazioni associate ad una
 * azione richiesta
 * 
 * @author Spin Servizi per l'Innovazione
 *
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetAzioneRichiesta extends ServiceRequest {
	
	private AzioneRichiesta azioneRichiesta;

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

}
