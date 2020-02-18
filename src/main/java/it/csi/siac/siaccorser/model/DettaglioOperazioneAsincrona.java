/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import javax.xml.bind.annotation.XmlType;

/**
 * Descrittore del dettaglio generata dall'esecuzione di un'operazione
 * asincrona
 * 
 * @author rmontuori
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class DettaglioOperazioneAsincrona extends Entita{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6767007560832544297L;
	private String esito;
	private String messaggio;
	private String codice;
	private String descrizione;
	private String serviceResponse;

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(String serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	
	

}
