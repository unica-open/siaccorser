/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.ClassificatoreGenerico;
import it.csi.siac.siaccorser.model.ServiceResponse;

/**
 * Messaggio di risposta per la lista delle codifiche di un operatore
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class LeggiCodificheResponse extends ServiceResponse {

	private List<ClassificatoreGenerico> listaClassificatori = new ArrayList<ClassificatoreGenerico>();

	public List<ClassificatoreGenerico> getListaClassificatori() {
		return listaClassificatori;
	}

	public void setListaClassificatori(List<ClassificatoreGenerico> listaClassificatori) {
		this.listaClassificatori = listaClassificatori;
	}

}
