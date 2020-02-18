/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.AttivitaPendente;
import it.csi.siac.siaccorser.model.ServiceResponse;

/**
 * Messaggio di risposta alla richiesta della lista delle
 * attività pendenti di un account
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetAttivitaPendentiResponse extends ServiceResponse {

	List<AttivitaPendente> attivitaPendenti = new ArrayList<AttivitaPendente>();

	/**
	 * @return the attivitaPendenti
	 */
	public List<AttivitaPendente> getAttivitaPendenti() {
		return attivitaPendenti;
	}

	/**
	 * @param attivitaPendenti the attivitaPendenti to set
	 */
	public void setAttivitaPendenti(List<AttivitaPendente> attivitaPendenti) {
		this.attivitaPendenti = attivitaPendenti;
	}

}
