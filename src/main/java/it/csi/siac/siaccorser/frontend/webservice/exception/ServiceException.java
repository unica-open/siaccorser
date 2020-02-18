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
 * Errore applicativo
 * 
 * @author alagna
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
public class ServiceException extends GenericException {

	private static final long serialVersionUID = -7753704341568224457L;

	public ServiceException(Errore errore) {
		super(errore);
	}

	public ServiceException(List<Errore> errori) {
		super(errori);
	}

	public ServiceException(String message) {
		super(message);
	}
}
