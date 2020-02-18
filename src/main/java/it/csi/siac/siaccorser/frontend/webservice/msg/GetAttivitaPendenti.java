/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.AzioneConsentita;
import it.csi.siac.siaccorser.model.ServiceRequest;

/**
 * Messaggio di richiesta della lista delle attività pendenti di un account
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetAttivitaPendenti extends ServiceRequest {

	private AzioneConsentita azioneConsentita;
	private Integer idEnteProprietario;
	private int offset = 0;
	private int size = 10;

	/**
	 * @return the azioneConsentita
	 */
	public AzioneConsentita getAzioneConsentita() {
		return azioneConsentita;
	}

	/**
	 * @param azioneConsentita
	 *            the azioneConsentita to set
	 */
	public void setAzioneConsentita(AzioneConsentita azioneConsentita) {
		this.azioneConsentita = azioneConsentita;
	}

	public Integer getIdEnteProprietario() {
		return idEnteProprietario;
	}

	public void setIdEnteProprietario(Integer idEnteProprietario) {
		this.idEnteProprietario = idEnteProprietario;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
