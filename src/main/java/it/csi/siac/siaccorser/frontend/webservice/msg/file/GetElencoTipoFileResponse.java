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
import it.csi.siac.siaccorser.model.file.TipoFile;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetElencoTipoFileResponse extends ServiceResponse {
	private List<TipoFile> elencoTipoFile = new ArrayList<TipoFile>();

	public List<TipoFile> getElencoTipoFile() {
		return elencoTipoFile;
	}

	public void setElencoTipoFile(List<TipoFile> elencoTipoFile) {
		this.elencoTipoFile = elencoTipoFile;
	}

}
