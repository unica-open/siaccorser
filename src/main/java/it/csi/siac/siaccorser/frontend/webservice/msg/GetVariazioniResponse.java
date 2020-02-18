/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.Variazione;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetVariazioniResponse extends ServiceResponse{

	List<Variazione> variazioni = new ArrayList<Variazione>();
	private Long totale;

	/**
	 * @return the variazioni
	 */
	public List<Variazione> getVariazioni() {
		return variazioni;
	}

	/**
	 * @param variazioni the variazioni to set
	 */
	public void setVariazioni(List<Variazione> variazioni) {
		this.variazioni = variazioni;
	}

	/**
	 * @return the totale
	 */
	public Long getTotale() {
		return totale;
	}

	/**
	 * @param totale the totale to set
	 */
	public void setTotale(Long totale) {
		this.totale = totale;
	}

	
	
}
