/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.ServiceResponse;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class FindAzioneResponse extends ServiceResponse {

	private Azione azione;

	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}
}
 