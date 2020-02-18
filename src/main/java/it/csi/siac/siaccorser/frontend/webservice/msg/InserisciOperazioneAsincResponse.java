/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceResponse;


/**
 * Messaggio di risposta alla richiesta di inserimento dell'operazione asincrona
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class InserisciOperazioneAsincResponse extends ServiceResponse {
	
	private String messaggio;
	private String stato;
	private Integer idOperazione;
	
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
			
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Integer getIdOperazione() {
		return idOperazione;
	}
	public void setIdOperazione(Integer idOperazione) {
		this.idOperazione = idOperazione;
	}

}
