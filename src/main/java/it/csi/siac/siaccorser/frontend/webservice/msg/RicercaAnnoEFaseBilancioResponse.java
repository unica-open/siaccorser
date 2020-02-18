/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.AnnoBilancio;
import it.csi.siac.siaccorser.model.ServiceResponse;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class RicercaAnnoEFaseBilancioResponse extends ServiceResponse {
	private AnnoBilancio annoEFaseBilancio;
	
	

	/**
	 * @return the annoEFaseBilancio
	 */
	public AnnoBilancio getAnnoEFaseBilancio() {
		return annoEFaseBilancio;
	}

	/**
	 * @param annoEFaseBilancio the annoEFaseBilancio to set
	 */
	public void setAnnoEFaseBilancio(AnnoBilancio annoEFaseBilancio) {
		this.annoEFaseBilancio = annoEFaseBilancio;
	}


}
