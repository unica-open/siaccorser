/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta di ricerca di una lista di attività 
 * pendenti (task del bpm)
 * 
 * @author Spin Servizi per l'Innovazione
 *
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class RicercaAttivitaPendenti extends ServiceRequest {
	
	ParametriRicercaTaskBpm parametriRicerca;

	/**
	 * @return the parametriRicerca
	 */
	public ParametriRicercaTaskBpm getParametriRicerca() {
		return parametriRicerca;
	}

	/**
	 * @param parametriRicerca the parametriRicerca to set
	 */
	public void setParametriRicerca(ParametriRicercaTaskBpm parametriRicerca) {
		this.parametriRicerca = parametriRicerca;
	}

}
