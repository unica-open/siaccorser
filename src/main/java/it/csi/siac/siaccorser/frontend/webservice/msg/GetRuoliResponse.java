/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.RuoloAccount;
import it.csi.siac.siaccorser.model.ServiceResponse;

@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class GetRuoliResponse extends ServiceResponse{

	List<RuoloAccount> ruoli = new ArrayList<RuoloAccount>();

	/**
	 * @return the ruoli
	 */
	public List<RuoloAccount> getRuoli() {
		return ruoli;
	}

	/**
	 * @param ruoli the ruoli to set
	 */
	public void setRuoli(List<RuoloAccount> ruoli) {
		this.ruoli = ruoli;
	}
	
}
