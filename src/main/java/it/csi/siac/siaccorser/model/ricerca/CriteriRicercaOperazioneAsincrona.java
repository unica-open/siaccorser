/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model.ricerca;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.model.CORDataDictionary;

/**
 * CriteriRicercaOperazioneAsincrona: entita' di supporto per la ricerca delle op.asinc
 * contiene i parametri (obbligatori e/o * facoltativi) di ricerca
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORDataDictionary.NAMESPACE)
public class CriteriRicercaOperazioneAsincrona implements Serializable {

	private static final long serialVersionUID = 1673551895423561852L;

	private Integer accountId;
	private Integer azioneId;
	private Integer enteProprietarioId;
	
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
	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the azioneId
	 */
	public Integer getAzioneId() {
		return azioneId;
	}
	/**
	 * @param azioneId the azioneId to set
	 */
	public void setAzioneId(Integer azioneId) {
		this.azioneId = azioneId;
	}
	/**
	 * @return the enteProprietarioId
	 */
	public Integer getEnteProprietarioId() {
		return enteProprietarioId;
	}
	/**
	 * @param enteProprietarioId the enteProprietarioId to set
	 */
	public void setEnteProprietarioId(Integer enteProprietarioId) {
		this.enteProprietarioId = enteProprietarioId;
	}
	
	

}
