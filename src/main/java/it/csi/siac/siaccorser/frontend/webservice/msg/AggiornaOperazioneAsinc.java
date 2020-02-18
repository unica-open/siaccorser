/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.StatoOperazioneAsincronaEnum;

/**
 * Messaggio di richiesta di aggiornamento dell'operazione asincrona
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class AggiornaOperazioneAsinc extends ServiceRequest {

	private Integer idOperazioneAsinc;
	private Integer idEnte;
	private String stato;

	public Integer getIdOperazioneAsinc() {
		return idOperazioneAsinc;
	}

	public void setIdOperazioneAsinc(Integer idOperazioneAsinc) {
		this.idOperazioneAsinc = idOperazioneAsinc;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public void setStato(StatoOperazioneAsincronaEnum stato) {
		setStato(stato.getCodice());
	}

}
