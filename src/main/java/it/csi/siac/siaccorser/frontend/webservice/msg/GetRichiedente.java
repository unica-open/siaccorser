/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ServiceRequest;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetRichiedente extends ServiceRequest {
	private String codiceAccount;

	public String getCodiceAccount() {
		return codiceAccount;
	}

	public void setCodiceAccount(String codiceAccount) {
		this.codiceAccount = codiceAccount;
	}

}
