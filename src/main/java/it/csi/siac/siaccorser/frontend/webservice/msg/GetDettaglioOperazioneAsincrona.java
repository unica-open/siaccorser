/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;


/**
 * Messaggio di richiesta di lettura del dettaglio dell'operazione asincrona
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetDettaglioOperazioneAsincrona extends ServiceRequest {
	
	private Integer opAsincId;
	private String codice;
	
	private ParametriPaginazione parametriPaginazione = new ParametriPaginazione();

	public Integer getOpAsincId() {
		return opAsincId;
	}

	public void setOpAsincId(Integer opAsincId) {
		this.opAsincId = opAsincId;
	}

	public ParametriPaginazione getParametriPaginazione() {
		return parametriPaginazione;
	}

	public void setParametriPaginazione(ParametriPaginazione parametriPaginazione) {
		this.parametriPaginazione = parametriPaginazione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
}
