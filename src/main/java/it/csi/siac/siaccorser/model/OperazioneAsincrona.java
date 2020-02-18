/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * OperazioneAsincrona
 * 
 * @author rmontuori
 * 
 **/
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class OperazioneAsincrona implements Serializable {

	private static final long serialVersionUID = 5723441380660830279L;

	private Integer idOpAsinc;
	private String messaggio;
	private String stato;
	
	public Integer getIdOpAsinc() {
		return idOpAsinc;
	}
	public void setIdOpAsinc(Integer idOpAsinc) {
		this.idOpAsinc = idOpAsinc;
	}
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

	
	

}
