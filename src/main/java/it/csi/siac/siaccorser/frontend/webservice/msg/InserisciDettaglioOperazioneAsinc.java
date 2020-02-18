/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;


/**
 * Messaggio di richiesta di inserimento del dettaglio dell'operazione asicrona
 * 
 * Vanno tracciate:
 * - una stringa contenente le info necessarie per tracciare il dato trattato nell'op.asincrona
 * - l'esito
 * - in caso di errore il motivo  
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class InserisciDettaglioOperazioneAsinc extends ServiceRequest {
	
	private Integer idOperazioneAsincrona;
	private Integer idEnte;
	
	private String codice;
	private String descrizione;
	private String esito;
	private String msgErrore;	
	private String serviceResponse;
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public String getMsgErrore() {
		return msgErrore;
	}
	public void setMsgErrore(String msgErrore) {
		this.msgErrore = msgErrore;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public Integer getIdOperazioneAsincrona() {
		return idOperazioneAsincrona;
	}
	public void setIdOperazioneAsincrona(Integer idOperazioneAsincrona) {
		this.idOperazioneAsincrona = idOperazioneAsincrona;
	}
	public Integer getIdEnte() {
		return idEnte;
	}
	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}
	public String getServiceResponse() {
		return serviceResponse;
	}
	public void setServiceResponse(String serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	
	
}
