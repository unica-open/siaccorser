/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta di abort di un processo 
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class AbortProcesso extends ServiceRequest {

	String idProcesso;

	/**
	 * @return the idProcesso
	 */
	public String getIdProcesso() {
		return idProcesso;
	}

	/**
	 * @param idProcesso the idProcesso to set
	 */
	public void setIdProcesso(String idProcesso) {
		this.idProcesso = idProcesso;
	}
}
