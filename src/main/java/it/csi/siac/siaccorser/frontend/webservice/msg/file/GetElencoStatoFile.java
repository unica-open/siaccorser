/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg.file;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.ServiceRequest;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetElencoStatoFile extends ServiceRequest {
	private Ente ente;

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public GetElencoStatoFile(Ente ente, Richiedente richiedente) {
		this();
		setEnte(ente);
		setRichiedente(richiedente);
	}

	public GetElencoStatoFile() {
		super();
	}

}
