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
 * Messaggio di richiesta di lettura dei messaggi di notifica dell'operazione asincrona
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetNotificheOperazioneAsincrona extends ServiceRequest {
	
	private Integer azioneId;
	private Integer enteProprietarioId;
	private Integer accountId;
	
	private String codiceStato;
	private String dataDa;
	private String dataA;
	private Boolean flagAltriUtenti;
	
	/**
	 * @return the codiceStato
	 */
	public String getCodiceStato() {
		return codiceStato;
	}
	/**
	 * @param codiceStato the codiceStato to set
	 */
	public void setCodiceStato(String codiceStato) {
		this.codiceStato = codiceStato;
	}

	/**
	 * @return the dataDa
	 */
	public String getDataDa() {
		return dataDa;
	}
	/**
	 * @param dataDa the dataDa to set
	 */
	public void setDataDa(String dataDa) {
		this.dataDa = dataDa;
	}
	/**
	 * @return the dataA
	 */
	public String getDataA() {
		return dataA;
	}
	/**
	 * @param dataA the dataA to set
	 */
	public void setDataA(String dataA) {
		this.dataA = dataA;
	}
	/**
	 * @return the flagAltriUtenti
	 */
	public Boolean getFlagAltriUtenti() {
		return flagAltriUtenti;
	}
	/**
	 * @param flagAltriUtenti the flagAltriUtenti to set
	 */
	public void setFlagAltriUtenti(Boolean flagAltriUtenti) {
		this.flagAltriUtenti = flagAltriUtenti;
	}
	private ParametriPaginazione parametriPaginazione = new ParametriPaginazione();
	
	public ParametriPaginazione getParametriPaginazione() {
		return parametriPaginazione;
	}
	public void setParametriPaginazione(ParametriPaginazione parametriPaginazione) {
		this.parametriPaginazione = parametriPaginazione;
	}
	
	public Integer getAzioneId() {
		return azioneId;
	}
	public void setAzioneId(Integer azioneId) {
		this.azioneId = azioneId;
	}
	public Integer getEnteProprietarioId() {
		return enteProprietarioId;
	}
	public void setEnteProprietarioId(Integer enteProprietarioId) {
		this.enteProprietarioId = enteProprietarioId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	

}
