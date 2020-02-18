/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.ServiceRequest;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class FindAzione extends ServiceRequest {

	private Ente ente;
	private Integer idAzione;
	private String nomeAzione;

	public Integer getIdAzione() { 
		return idAzione;
	}

	public void setIdAzione(Integer idAzione) {
		this.idAzione = idAzione;
	}

	public String getNomeAzione() {
		return nomeAzione;
	}

	public void setNomeAzione(String nomeAzione) {
		this.nomeAzione = nomeAzione;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}
}
