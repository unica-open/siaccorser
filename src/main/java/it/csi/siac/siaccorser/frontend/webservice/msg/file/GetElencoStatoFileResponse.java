/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg.file;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceResponse;
import it.csi.siac.siaccorser.model.file.StatoFile;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetElencoStatoFileResponse extends ServiceResponse {
	private List<StatoFile> elencoStatoFile = new ArrayList<StatoFile>();

	public List<StatoFile> getElencoStatoFile() {
		return elencoStatoFile;
	}

	public void setElencoStatoFile(List<StatoFile> elencoStatoFile) {
		this.elencoStatoFile = elencoStatoFile;
	}

}
