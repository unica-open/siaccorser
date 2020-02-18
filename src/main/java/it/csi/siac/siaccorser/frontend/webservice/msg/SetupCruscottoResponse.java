/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

package it.csi.siac.siaccorser.frontend.webservice.msg;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.Cruscotto;
import it.csi.siac.siaccorser.model.ServiceResponse;

import javax.xml.bind.annotation.XmlType;

/**
 * Messaggio di risposta alla richiesta delle informazioni visualizzate nel
 * cruscotto di un operatore
 * 
 * @author Spin Servizi per l'Innovazione
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class SetupCruscottoResponse extends ServiceResponse {

	private Cruscotto cruscotto;
	private boolean isUtenteVariaizoniDecentrato;
	private Integer idAzionePerUtenteDecentrato;
	
	
	/**
	 * @return the idAzionePerUtenteDecentrato
	 */
	public Integer getIdAzionePerUtenteDecentrato() {
		return idAzionePerUtenteDecentrato;
	}

	/**
	 * @param idAzionePerUtenteDecentrato the idAzionePerUtenteDecentrato to set
	 */
	public void setIdAzionePerUtenteDecentrato(Integer idAzionePerUtenteDecentrato) {
		this.idAzionePerUtenteDecentrato = idAzionePerUtenteDecentrato;
	}

	/**
	 * @return the isUtenteVariaizoniDecentrato
	 */
	public boolean isUtenteVariaizoniDecentrato() {
		return isUtenteVariaizoniDecentrato;
	}

	/**
	 * @param isUtenteVariaizoniDecentrato the isUtenteVariaizoniDecentrato to set
	 */
	public void setUtenteVariaizoniDecentrato(boolean isUtenteVariaizoniDecentrato) {
		this.isUtenteVariaizoniDecentrato = isUtenteVariaizoniDecentrato;
	}

	/**
	 * @return the cruscotto
	 */
	public Cruscotto getCruscotto() {
		return cruscotto;
	}

	/**
	 * @param cruscotto
	 *            the cruscotto to set
	 */
	public void setCruscotto(Cruscotto cruscotto) {
		this.cruscotto = cruscotto;
	}

}
