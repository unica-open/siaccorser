/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.Errore;

/**
 * Errore di Sistema
 * 
 * @author alagna
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class SystemException extends GenericException {

	private static final long serialVersionUID = 2410425098041724712L;

	public SystemException(Errore errore) {
		super(errore);
	}

	public SystemException(List<Errore> errori) {
		super(errori);
	}
}
