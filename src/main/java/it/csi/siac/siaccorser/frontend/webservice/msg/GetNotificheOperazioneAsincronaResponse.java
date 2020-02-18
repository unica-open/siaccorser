/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.NotificaOperazioneAsincrona;
import it.csi.siac.siaccorser.model.PagedResponse;

/**
 * Messaggio di risposta alla richiesta di lettura dei messaggi di notifica
 * dell'operazione asincrona
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
@XmlSeeAlso(NotificaOperazioneAsincrona.class)
public class GetNotificheOperazioneAsincronaResponse extends PagedResponse<NotificaOperazioneAsincrona> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 399581146105544010L;



}
