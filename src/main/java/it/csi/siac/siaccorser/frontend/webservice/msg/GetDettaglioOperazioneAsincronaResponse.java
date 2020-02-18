/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.frontend.webservice.msg;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siaccorser.frontend.webservice.CORSvcDictionary;
import it.csi.siac.siaccorser.model.DettaglioOperazioneAsincrona;
import it.csi.siac.siaccorser.model.PagedResponse;

/**
 * Messaggio di risposta alla richiesta di lettura dei dettagli dell'operazione asincrona
 * 
 * @author rmontuori
 * 
 */
@XmlType(namespace = CORSvcDictionary.NAMESPACE)
@XmlSeeAlso(DettaglioOperazioneAsincrona.class)
public class GetDettaglioOperazioneAsincronaResponse extends PagedResponse<DettaglioOperazioneAsincrona> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6724472367431795401L;


}
