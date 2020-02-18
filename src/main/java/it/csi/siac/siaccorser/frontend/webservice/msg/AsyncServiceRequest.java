/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.CORDataDictionary;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.ServiceRequest;

@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class AsyncServiceRequest extends ServiceRequest {
	
	private Integer idOperazioneAsincrona;	
	private Ente ente;

	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	/**
	 * @return the idOperazioneAsincrona
	 */
	public Integer getIdOperazioneAsincrona() {
		return idOperazioneAsincrona;
	}

	/**
	 * @param idOperazioneAsincrona the idOperazioneAsincrona to set
	 */
	public void setIdOperazioneAsincrona(Integer idOperazioneAsinc) {
		this.idOperazioneAsincrona = idOperazioneAsinc;
	}

	

}
