/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.integration.bpm.msg;

import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Richiesta del metodo getTaskInfo
 * 
 * @author alagna
 * @version $Id: $
 */
public class GetTaskInfo extends ServiceRequest {
	
	private String idAttivita;

	/**
	 * @return the idAttivita
	 */
	public String getIdAttivita() {
		return idAttivita;
	}

	/**
	 * @param idAttivita the idAttivita to set
	 */
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}
}
