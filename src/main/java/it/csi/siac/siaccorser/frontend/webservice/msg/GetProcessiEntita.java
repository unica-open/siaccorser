/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.EntitaDem;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta della lista dei processi associati ad 
 * una entità
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetProcessiEntita extends ServiceRequest {

	EntitaDem entita;
	boolean richiestaListaTask = false;
	/**
	 * @return the entita
	 */
	public EntitaDem getEntita() {
		return entita;
	}
	/**
	 * @param entita the entita to set
	 */
	public void setEntita(EntitaDem entita) {
		this.entita = entita;
	}
	/**
	 * @return the richiestaListaTask
	 */
	public boolean isRichiestaListaTask() {
		return richiestaListaTask;
	}
	/**
	 * @param richiestaListaTask the richiestaListaTask to set
	 */
	public void setRichiestaListaTask(boolean richiestaListaTask) {
		this.richiestaListaTask = richiestaListaTask;
	}
	
}
