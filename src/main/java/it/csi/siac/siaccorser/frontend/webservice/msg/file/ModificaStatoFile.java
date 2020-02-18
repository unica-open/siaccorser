/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg.file;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.file.StatoFile.CodiceStatoFile;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class ModificaStatoFile extends ServiceRequest {
	private Ente ente;
	private Integer uid;
	private CodiceStatoFile stato;

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public CodiceStatoFile getStato() {
		return stato;
	}

	public void setStato(CodiceStatoFile stato) {
		this.stato = stato;
	}

}
